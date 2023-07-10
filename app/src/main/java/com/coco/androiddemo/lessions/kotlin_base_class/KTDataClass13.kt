package com.coco.androiddemo.lessions.kotlin_base_class

// 内联类

// 有时候，业务逻辑需要围绕某种类型创建包装器。然而，由于额外的堆内存分配问题，它会引入运行时的性能开销。
// 此外，如果被包装的类型是原生类型，性能的损失是很糟糕的，
// 因为原生类型通常在运行时就进行了大量优化，然而他们的包装器却没有得到任何特殊的处理。
//为了解决这类问题，Kotlin 引入了一种被称为 内联类 的特殊类，它通过在类的前面定义一个 inline 修饰符来声明：

inline class Password(val password: String) {

}

// 内联类支持普通类中的一些功能。特别是，内联类可以声明属性与函数
// 内联类不能含有 init 代码块
// 内联类不能含有幕后字段, 内联类只能含有简单的计算属性

inline class Name(val s: String) {
    val length: Int
        get() = s.length

    fun test() {

    }
}

// 继承
// 内联类允许去继承接口

interface Printable {
    fun prettyPrint(): String
}

inline class Lession(val s: String): Printable {
    val length: Int
        get() = s.length
    override fun prettyPrint(): String {
        return  "Lession $s"
    }
}


