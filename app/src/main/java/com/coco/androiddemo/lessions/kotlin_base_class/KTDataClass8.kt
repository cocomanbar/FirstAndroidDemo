package com.coco.androiddemo.lessions.kotlin_base_class

fun main() {

}

// 密封类
// 密封类用来表示受限的类继承结构：当一个值为有限几种的类型、而不能有任何其他类型时。
// 在某种意义上，他们是枚举类的扩展：枚举类型的值集合也是受限的，
// 但每个枚举常量只存在一个实例，而密封类的一个子类可以有可包含状态的多个实例

// 密封类是自身抽象的，它不能直接实例化并可以有抽象（abstract）成员

sealed class Expr

data class KTDataClass8(val number: Double): Expr()

data class Sum(val e1: Expr, val e2: Expr): Expr()

object NotANumber: Expr()

// 使用密封类的关键好处在于使用 when 表达式 的时候，如果能够验证语句覆盖了所有情况，就不需要为该语句再添加一个 else 子句了。
// 当然，这只有当你用 when 作为表达式（使用结果）而不是作为语句时才有用

fun eval(expr: Expr): Double = when(expr) {
    is KTDataClass8 -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    // 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
    // 子类不需要全部写在一个文件内,也可以检测完所有的情况
}
