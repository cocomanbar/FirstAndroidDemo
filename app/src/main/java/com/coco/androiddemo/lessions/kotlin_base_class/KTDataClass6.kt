package com.coco.androiddemo.lessions.kotlin_base_class

import kotlin.reflect.typeOf

fun main() {

    val list = mutableListOf<Int>(1, 2, 3, 4)
    list.swap(0, 1)
    println("${list.toString()}")

    val shape = Shape()
    val rectangle = Rectangle()
    testPrintln1(shape)          // Shape
    testPrintln1(rectangle)      // Rectangle
    testPrintln2(shape)          // Shape
    testPrintln2(rectangle)      // Shape

    val string: String? = null
    //string = "哈哈"
    println("string = $string")
    println(string.toString())

    val house = House()
    println(house.age)
    println(house.name)
    println(house.address)
    house.address = "hehe"

    MyClass.test()
    MyClass.MyCompanion.test()

    val host = Host()
    val connect1 = Connect1(host, 2)
    connect1.connect()
}

// 扩展
// 扩展 的特殊声明
// 够扩展一个类的新功能而无需继承该类或者使用像装饰者这样的设计模式


// 扩展函数
// 声明一个扩展函数，我们需要用一个 接收者类型 也就是被扩展的类型来作为他的前缀

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

// 奇怪的知识点
// 扩展函数是静态分发的，即他们不是根据接收者类型的虚方法。
// 这意味着调用的扩展函数是由函数调用所在的表达式的类型来决定的， 而不是由表达式运行时求值结果决定的
open class Shape
class Rectangle: Shape()

fun Shape.getName(): String {
    return "Shape"
}

fun Rectangle.getName(): String {
    return "Rectangle"
}

fun testPrintln1(s: Shape) {
    if (s is Rectangle) {
        println(s.getName())
    } else if (s is Shape) {
        println(s.getName())
    } else {
        println("...")
    }
}

fun testPrintln2(s: Shape) {
    println(s.getName())
}

// 可空接收者
fun Any?.toString(): String {
    if (this == null) return "null"
    return toString()
}


// 扩展属性
//val <T> List<T>.lastIndex: Int
//    get() = size - 1

// 由于扩展没有实际的将成员插入类中，因此对扩展属性来说幕后字段是无效的。这就是为什么扩展属性不能有初始化器。
// 他们的行为只能由显式提供的 getters/setters 定义

class House {

}

val House.age: Int
    get() = 1

val House.name: String
    get() = "哈哈哈"

var House.address: String
    get() = "???"
    set(value) {
        println(value)
    }


// 静态 类似 java的static 方法
// 伴生对象的扩展
class MyClass {
    companion object {}

    object MyCompanion {
        fun test() {
            println("MyClass.MyCompanion.test")
        }
    }
}

fun MyClass.Companion.test() {
    println("MyClass.Companion.test")
}

// 扩展声明为成员
// 扩展声明所在的类的实例称为 分发接收者，扩展方法调用所在的接收者类型的实例称为 扩展接收者

class Host {
    fun test() {
        println("Host.test")
    }
}

class Connect1(private val host: Host, private val port: Int) {

    private fun printPort() {
        println("Connect.port = $port")
    }

    // Host扩展一个函数
    private fun Host.test2() {
        test()
        printPort()

        val str1 = toString()                   // 指向 扩展接收者 Host
        val str2 = this.toString()              // 指向 扩展接收者 Host
        val str3 = this@Connect1.toString()     // 指向 分发接收者 Connect1
        println("str1 = $str1, str2 = $str2, str3 = $str3")
    }

    fun connect() {
        host.test2()
    }
}

class Connect2(val host: Host, val port: Int) {

    fun printPort() {
        println("Connect.port = $port")
    }

    // Host扩展一个函数
    fun Host.test3() {
        test()
        printPort()
    }

    fun connect() {
        host.test3()
    }
}

class Tester(val host: Host) {
    fun test1() {
        host.test()

        // 找不到
        //host.test2()
        //host.test3()
    }
}

