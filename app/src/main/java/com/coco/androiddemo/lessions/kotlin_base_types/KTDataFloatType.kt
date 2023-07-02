package com.coco.androiddemo.lessions.kotlin_base_types

fun main() {

    var number5 = 235
    println("number5 is Int = ${(number5 is Int)}")
    var number6 = 3.14159f
    println("number6 is Float = ${(number6 is Float)}")

    var number7 = number5 + number6
    println("number7 is  = $number7")

    // 这里是不是 + 运算符 做了事情
    // Int + Double 可以直接相加?

    // swift 需要转换
}