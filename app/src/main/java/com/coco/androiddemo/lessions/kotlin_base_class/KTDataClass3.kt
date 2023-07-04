package com.coco.androiddemo.lessions.kotlin_base_class

import java.util.jar.Attributes.Name

// 接口

fun main() {

}

// 关键字 interface 来定义接口
interface MyInterface {

    // 可以声明
    fun test1()

    // 可以声明加实现，但是无法保存状态
    fun test2() {
        println("MyInterface test2")
    }

    // 接口中定义属性，要么是抽象的，要么提供访问器的实现
    // 属性不能有幕后字段（backing field），因此接口中声明的访问器不能引用它们
    var name: String
}

class Child: MyInterface {

    // 因为test1 接口里没有默认实现，所以这里需要实现，不然爆红
    override fun test1() {
        println("test1")
    }

    override fun test2() {
        super.test2()
        println("Child test2")
    }

    override var name: String = ""
        get() = name
        set(value) {
            field = value
        }
}

// 接口继承
interface Named {
    val name: String
}

interface Person: Named {

    val firstName: String
    val lastName: String

    override val name: String
        get() = "$firstName $lastName"
}

class Employee: Person {

    override val firstName: String
        get() = "谭"

    override val lastName: String
        get() = "coco"

}

class Employee1(override val firstName:String,
                override val lastName:String): Person
{

}

class Employee2(override val firstName:String,
                override val lastName:String,
                val address: String): Person
{

}

// 解决覆盖冲突，多接口实现

interface A {
    fun test1() { println("A.test1") }
    fun test2()
}

interface B {
    fun test1() { println("B.test1") }
    fun test2() { println("B.test2") }
}

class C: A {
    // 需要实现抽象方法 test2
    override fun test2() {
    }
    // 可选择实现test1，或覆盖
}

class D: B {
    // 可选择实现test1，或覆盖
    // 可选择实现test2，或覆盖
}

class E: A, B {

    // 通过此方式选择调用其中一个接口，多个，也可以覆盖
    override fun test1() {
        super<A>.test1()
        super<B>.test1()
    }

    override fun test2() {
        super<B>.test1()
    }
}








