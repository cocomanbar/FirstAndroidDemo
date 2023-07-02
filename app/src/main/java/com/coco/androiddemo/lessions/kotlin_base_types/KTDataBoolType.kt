package com.coco.androiddemo.lessions.kotlin_base_types

import kotlin.reflect.typeOf

fun main() {

    // Bool
    var b1 = true
    b1 = false
    val b2: Boolean = false


    // 位运算
    // and or xor

    val bo1 = true
    val bo2 = false
    val bo3 = bo2.and(bo1)
    val bo4 = bo2.or(bo1)
    val bo5 = bo2.xor(bo1)
    println("bo3" + bo3)
    println("bo4" + bo4)
    println("bo5" + bo5)

}























