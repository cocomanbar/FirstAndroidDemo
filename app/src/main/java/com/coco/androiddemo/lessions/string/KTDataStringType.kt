package com.coco.androiddemo.lessions.string

fun main() {

    val str1 = "字符串"

    var str2 = str1

    val str3 = str2 + str1

    println("蒸饭车 = $str3")

    for (c in str3) {
        println("c = $c, c is ${(c is Char)}")
    }

    val str4 = """
        tel:
        132xxxxx4581
        {"key":"value"}
    """.trimIndent()

    var str5 = "{\"key\":\"value\"}"

    // toXXX()
    val str6 = "6"
    val number1 = str6.toInt()



}

