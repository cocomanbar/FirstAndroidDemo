package com.coco.androiddemo.lessions.kotlin_base_class

fun main() {

}

// 嵌套类与内部类

// 类可以嵌套在其他类中
class Tester1 {
    private val bar1: Int = 1
    class Nedsted1 {
        private val bar2: Int = 1
        fun test(): Unit {
            println("$bar2")
            // println("${super@Tester2.bar1}") 报错
        }
    }
}

// 标记为 inner 的嵌套类能够访问其外部类的成员。内部类会带有一个对外部类的对象的引用
class Tester2 {
    private val bar1: Int = 1
    inner class Nedsted2 {
        private val bar2: Int = 1
        fun test(): Unit {
            println("${this.bar2}")
            println("${super@Tester2.bar1}")
        }
    }
}




