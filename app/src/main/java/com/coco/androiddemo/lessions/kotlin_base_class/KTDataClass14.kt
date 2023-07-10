package com.coco.androiddemo.lessions.kotlin_base_class

// 委托

// Derived 类可以通过将其所有公有成员都委托给指定对象来实现一个接口 Base:

interface Base {
    fun print()
}

class BaseImpl(private val x: Int): Base {
    override fun print() {
        print(x)
        println("??")
    }
}

class Derived(b: Base) : Base by b

//fun main() {
//
//    val b = BaseImpl(10)
//    Derived(b).print()
//}


// 覆盖由委托实现的接口成员

interface Base1 {
    fun printMessage1()
    fun printMessage2()
}

class BaseImpl1(val x: Int) : Base1 {

    override fun printMessage1() {
        println(x)
    }

    override fun printMessage2() {
        println(x)
    }
}

class Derived1(b: Base): Base by b {

}

fun main() {

    val b = BaseImpl(10)

    // 报错了。。。
//    Derived1(b).printMessage1()
//    Derived1(b).printMessage2()
}





















