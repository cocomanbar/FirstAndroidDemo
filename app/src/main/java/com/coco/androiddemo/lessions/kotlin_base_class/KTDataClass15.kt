package com.coco.androiddemo.lessions.kotlin_base_class

import kotlin.properties.Delegates
import kotlin.reflect.KProperty
import kotlin.properties.Delegates.observable

// 委托属性

// 延迟属性（lazy properties）: 其值只在首次访问时计算
// 可观察属性（observable properties）: 监听器会收到有关此属性变更的通知
// 把多个属性储存在一个映射（map）中，而不是每个存在单独的字段中

// 为了涵盖这些（以及其他）情况，Kotlin 支持 委托属性:

// 语法是： val/var <属性名>: <类型> by <表达式>。
// 在 by 后面的表达式是该 委托， 因为属性对应的 get()（与 set()）会被委托给它的 getValue() 与 setValue() 方法

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class Example {
    var p: String by Delegate()
}

fun main() {

    val e = Example()
    println(e.p)
    e.p = "Name"

    // 延迟属性 Lazy
    val e1 = Example1()
    println(e1.lazyValue)

    // 可观察属性
    val e2 = Example2()
    e2.name1 = "哈哈1"
    e2.name2 = "哈哈2"

    // 将属性储存在映射中
    val e3 = Example3(mapOf(
        "name" to "哈哈",
        "age" to 5
    ))
    println(e3.name)

}



// 标准委托
// Kotlin 标准库为几种有用的委托提供了工厂方法。


// 延迟属性 Lazy
class Example1 {
    // 无需return，lazy标记那个就返回那个，最后一个
    val lazyValue: String by lazy {
        println("???")
        "哈哈哈1"
        "哈哈哈2"
    }
}

// 可观察属性 Observable

class Example2 {

    // 被赋值的属性、旧值与新值
    var name1: String by Delegates.observable("haha") {
        prop, old, new ->
        println("$old -> $new")
    }

    var name2: String? by Delegates.observable(null) {
            prop, old, new ->
        println("$old -> $new")
    }
}


// 将属性储存在映射中
// 这经常出现在像解析 JSON 或者做其他“动态”事情的应用中
class Example3(private val map: Map<String, Any?>) {
    val name: String? by map
    val age: Int? by map
}























