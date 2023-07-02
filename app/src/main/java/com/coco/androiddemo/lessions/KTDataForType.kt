package com.coco.androiddemo.lessions

fun main() {

    val str = "123456789"

    for (c in str) {
        println("c = $c, c is char? ${(c is Char)}")
    }

    // it 单独参数可用
    // swift $0 $1 ..
    str.forEach {
        println(it)
    }

    for (index in str.indices) {

    }

    for ((index, value) in str.withIndex()) {

    }

    var num = 1
    while (num < 5) {
        num += 1
    }
    do {
        num += 1
    } while (num < 10)
    println("结束")

}