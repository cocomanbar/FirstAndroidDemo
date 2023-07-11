package com.coco.androiddemo.lessions.functions

// 函数

// 函数声明

fun test1() {}
fun test2() : Unit {}
fun test3(name: String) {}
fun test4(name: String, age: Int): Unit {}
fun test5(name: String, age: Int): String { return "" }

// 默认参数
// A default value is defined using the = after the type.
fun test6(name: String = "小明") {}
fun test7() {
    test6()
    test6("小红")
}

// 如果最后一个参数是 lambda表达式，两种写法
fun test8(name: String, completion: () -> Unit) {}
fun test9() {

    // 可以在括号外传入
    test8(name = "表达式1") {

    }

    // 具名参数在括号内传入
    test8(name = "表达式2", completion = {

    })
}


// 单表达式函数
// 函数返回单个表达式
fun test10(x: Int) : Int = x * 2
// 类型推断
fun test11(x: Int) = x * 2


// 显式返回类型
// 具有块代码体的函数必须始终显式指定返回类型，除非他们旨在返回 Unit

fun test12(x: Int, y: Int): Int {
    return x * y
}


// 中缀表示 infix

infix fun Int.shlllll(x: Int): Int {
    return this + x
}

fun main() {

    // 使用中缀
    val x = 1 shlllll 5
    println(x)

    // 同于
    val y = 1.shlllll(5)
    println(y)

}



// 函数作用域
// 函数可以在文件顶层声明
// 声明在局部作用域、作为成员函数以及扩展函数

// 支持局部函数
// 局部函数可以访问外部函数（即闭包）的局部变量
fun test20(name: String) {
    fun test21() {
        println("$name")
    }
}


// 成员函数
// 成员函数是在类或对象内部定义的函数：

class Sample {
    fun foo() {

    }

    fun ffo() {
        Sample().foo()
    }
}


// 泛型函数
// 函数可以有泛型参数，通过在函数名前使用尖括号指定：

fun <T> test30(name: String, age: T) {

}























