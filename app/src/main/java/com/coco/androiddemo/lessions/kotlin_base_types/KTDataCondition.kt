package com.coco.androiddemo.lessions


// if else
// when
// when 增强版，代替switch -case
// when 替代嵌套if else

fun main() {

    val ret = true
    if (ret) {
        println("ret = $ret")
    } else {
        println("ret = $ret")
    }

    testWhen(5)

    testWhen2(5)
    testWhen2(2)
}

fun testWhen(value: Any) {
    when(value) {
        (value is Int) -> println("is Int")
        (value is Double) -> println("is Double")
        else -> { // 多行可以使用块
            println("未知类型： $value")
            println("结束")
        }
    }
}

fun testWhen2(value: Int) {
    when(value){
        2 -> println(2)
        3 -> println(3)
        4 -> println(4)
        else -> println("其他")
    }
}
