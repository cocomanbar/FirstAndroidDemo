package com.coco.androiddemo.lessions.kotlin_base_class

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

fun main() {

}

// 枚举类
// 每个枚举常量都是一个对象。枚举常量用逗号分隔。
enum class Direction {
    North, South, West, East
}

enum class Color(val rgb: Int) {
    RED(0xff0000),
    GREEN(0x00ff00),
    BLUE(0x0000ff)
}

// 匿名类
enum class ProtocolState {

    Talking {
        override fun signal(): ProtocolState {
            return  Talking
        }
    },

    // 如果枚举类定义任何成员，那么使用分号将成员定义中的枚举常量定义分隔开
    Waiting {
       override fun signal() = Waiting
    };

    abstract fun signal(): ProtocolState
}


// 在枚举类中实现接口

enum class IntArithmetics: BinaryOperator<Int>, IntBinaryOperator {

    Plus {
        override fun apply(p0: Int, p1: Int): Int {
            return  p0 + p1
0        }
    },

    Times {
        override fun apply(p0: Int, p1: Int): Int {
            return p0 * p1
        }
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t,u)
}





















