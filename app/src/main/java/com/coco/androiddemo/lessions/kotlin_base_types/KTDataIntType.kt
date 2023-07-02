package com.coco.androiddemo.lessions.kotlin_base_types

fun main() {

    // Int
    // 1. 声明类型，显式/隐式声明

    // 以未超出 Int 最大值的整型值初始化的变量都会推断为 Int 类型
    // 默认数据类型是int，超过了长度为long
    val number1 = 100
    val number2: Int = 100
    println("number1 is Int = ${(number1 is Int)}")

    var number3 = number1 + number2
    println(number3)
}