package com.coco.androiddemo.lessions

import android.content.Context
import android.util.AttributeSet
import android.view.View
import java.util.jar.Attributes

// 类与继承
fun main() {

    val cls = KTDataClassEmpty7(name1 = "哈哈")

}

// 简单声明一个类 从 Any 隐式继承
// 在 Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类

class KTDataClassEmpty1

// 可继承，请用 open
open class KTDataClassEmpty2 {}

// 被继续的写法，和swift差不多，少了个()
class KTDataClassEmpty22: KTDataClassEmpty2() {

}

// 主构造函数
class KTDataClassEmpty3 constructor(firstName: String?) {

}

// 主构造函数- 注解或者可见性修饰符
class KTDataClassEmpty4(firstName: String?) {

    val firstProperty: String = "哈哈$firstName"

    init {
        println("firstName = $firstName")
    }
}

class KTDataClassEmpty5(
    name: String?,
    age: Int,
    address: String?
) {

}

//  constructor 不可少
class KTDataClassEmpty6 public  constructor(name: String) {

}

// 次构造函数
// 先执行 init -> constructor
class KTDataClassEmpty7 {

    var name: String? = null

    init {
        println("name1 = $name")
    }

    constructor(name1: String) {
        name = name1
        println("name2 = $name")
    }
}

// 派生类没有主构造函数，
// 那么每个次构造函数必须使用 super 关键字初始化其基类型，或委托给另一个构造函数做到这一点
class MyView: View {
    constructor(ctx: Context): super(ctx)
    constructor(ctx: Context, attrs: AttributeSet): super(ctx, attrs)
}

// 重写和覆盖方法

open class Shape {
    open fun test1() {}
    fun test2() {}
}

class Circle: Shape() {
    override fun test1() {
        super.test1()
    }

    // test2 不允许子类有同名未开放的函数- 签名
}

// 报错了，因为 Circle 未使用 open
//class Circle1: Circle() {}

open class Circle1 {
    open fun test1() {

    }
}

open class Circle2: Circle1() {
    final override fun test1() {
        super.test1()
    }
}

// 这个时候因为 test1 -> final 修饰，终止被再次 重写。
class Circle3: Circle2() {
    // test1
}



// 属性覆盖与方法覆盖类似

open class Circle4 {

    open val count: Int = 0
    open val count2: Int = 0
    open val count3: Int = 0
    open var count4: Int = 0
}

class Circle5: Circle4() {

    // 用一个 var 属性覆盖一个 val 属性，但反之则不行
    override var count: Int = 0

    override val count2: Int
        get() = super.count2

    override var count3: Int
        get() = super.count3
        set(value) {
            count3 = value
        }

    override var count4: Int
        get() = super.count4
        set(value) {
            super.count4 = value
        }
}


// 在一个内部类中访问外部类的超类，可以通过由外部类名限定的 super 关键字来实现：super@Outer：

open class Circle6 {

    open fun testAbc() {
        println("Circle6.test1")
    }

    inner class Circle7 {
        fun test() {
            // 这里没有super可言，super是Any了
            // super@Circle6.testAbc()
            println("Circle7.test")
        }
    }
}


open class Circle8 {
    open fun draw() { println("Drawing a rectangle") }
    val borderColor: String get() = "black"
}

class Circle9: Circle8() {
    override fun draw() {
        val filler = Filler()
        filler.drawAndFill()
    }

    inner class Filler {
        private fun fill() { println("Filling") }
        fun drawAndFill() {
            super@Circle9.draw() // 调用 Rectangle 的 draw() 实现
            fill()
            println("Drawn a filled rectangle with color ${super@Circle9.borderColor}") // 使用 Rectangle 所实现的 borderColor 的 get()
        }
    }
}


// 抽象类 abstract 本类可以不用实现方法, 并不需要用 open

abstract class Palygon {
    open fun draw() {}
}

class Rectangle: Palygon() {
    override fun draw() {
        super.draw()

    }
}




