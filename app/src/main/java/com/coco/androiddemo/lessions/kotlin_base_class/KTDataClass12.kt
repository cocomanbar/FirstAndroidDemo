package com.coco.androiddemo.lessions.kotlin_base_class

fun main() {

    val ab: Aer = object : Aer(1), Ber {

    }

    // 类型别名
    // 类似函数嵌套函数？
    val f: (Int) -> Boolean = {
        it > 0
    }
    println(foo(f))

    val p: Predicate<Int> = {
        it > 0
    }
    println(listOf(1, -2).filter(p))
}

// 1.对象表达式与对象声明

open class Aer(val x: Int) {
    public open val y: Int = x
}

interface Ber {}


// 任何时候，如果我们只需要“一个对象而已”，并不需要特殊超类型，那么我们可以简单地写

fun test() {
    val adHot = object {
        var x = 5
        var y = 5
    }
    println(adHot.x + adHot.y)
}

// 2.类别别名

typealias IntSet = Set<Int>

typealias Handler = (Int, String) -> Unit


class Aer1 {
    inner class Ber {

    }
}

typealias ABer = Aer1.Ber

// 在代码中添加 typealias Predicate<T> 并使用 Predicate<Int> 时，
// Kotlin 编译器总是把它扩展为 (Int) -> Boolean

typealias Predicate<T> = (T) -> Boolean

fun foo(p: Predicate<Int>) = p(42)

















