package com.coco.androiddemo.lessions.kotlin_base_class

// 可见性修饰符

fun main() {

}

// 四个可见性修饰符：private、 protected、 internal 和 public

// 包
// 默认 public, 这意味着你的声明将随处可见
// private，它只会在声明它的文件内可见
// internal，它会在相同模块内随处可见
// protected 不适用于顶层声明

// 类和接口
// private 意味着只在这个类内部（包含其所有成员）可见
// protected—— 和 private一样 + 在子类中可见
// internal —— 能见到类声明的 本模块内 的任何客户端都可见其 internal 成员
// public —— 能见到类声明的任何客户端都可见其 public 成员


open class Food {

    private val a = 1
    protected val b = 2
    internal val c = 3
    val d = 4

    private fun test1() {

    }

    internal fun test2() {

    }

    fun test3() {

    }
}

class SubFood: Food() {
    // a不可见
    // bcd 可见

}
