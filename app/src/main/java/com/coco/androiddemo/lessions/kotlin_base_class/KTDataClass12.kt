package com.coco.androiddemo.lessions.kotlin_base_class

// 对象表达式与对象声明

fun main() {

    val ab: Aer = object : Aer(1), Ber {

    }

}


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
