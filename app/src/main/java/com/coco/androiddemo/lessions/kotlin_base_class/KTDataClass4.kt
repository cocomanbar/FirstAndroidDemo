package com.coco.androiddemo.lessions.kotlin_base_class

// 函数式接口 SAM

fun main() {

    val test = Test()
    println("${test.isEven1.test1(false)}")
    println("${test.isEven2.test1(true)}")
    test.isEven3.test1()
}

// 只有一个抽象方法的接口称为函数式接口或 SAM（单一抽象方法）接口。
// 函数式接口可以有多个非抽象成员，但只能有一个抽象成员

fun interface KRun1 {
    fun test1(): Unit
}

fun interface KRun2 {
    fun test1(b1: Boolean): Boolean
}

class Test {

    // 提示转为 lambda
    val isEven1 = object : KRun2 {
        override fun test1(b1: Boolean): Boolean {
            return !b1
        }
    }

    // 使用 lambda
    val isEven2 = KRun2 { !it }

    val isEven3 = KRun1 { println("test1") }

}