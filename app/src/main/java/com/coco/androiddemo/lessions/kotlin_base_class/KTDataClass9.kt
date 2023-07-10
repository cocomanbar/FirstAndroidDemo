package com.coco.androiddemo.lessions.kotlin_base_class

fun main() {

    // 显式
    var class9 = KTDataClass9<Int>(9)

    // 隐式
    var class10 = KTDataClass9(9)


}

// 泛型
class KTDataClass9<T>(t: T) {
    var value = t
}

// 声明处型变（declaration-site variance）与类型投影（type projections）
// 消费者 in, 生产者 out


// 泛型函数
fun <T> test(item: T): Unit {

}


// 类型擦除


