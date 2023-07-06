package com.coco.androiddemo.lessions.kotlin_base_class

fun main() {

    val user = User("haha", 1)
    val (name, age) = user  // 解构声明
    println("name1 = $name, age = $age")

    val student = Student()
    student.age = 18
    println("name2 = ${student.name}, age = ${student.age}")

    val student1 = student.copy()
    println("name3 = ${student1.name}, age = ${student1.age}")

    val student2 = student.copyStudent()
    println("name4 = ${student2.name}, age = ${student2.age}")


}

// 数据类
// 只保存数据的类，标记为 data
// 数据类必须满足以下要求：
// - 主构造函数需要至少有一个参数；
// - 主构造函数的所有参数需要标记为 val 或 var；
// - 数据类不能是抽象、开放、密封或者内部的；

data class User(val name: String, val age: Int)


// 无参的data类，主构造必须有默认值
data class Student(val name: String = "小明") {
    var age: Int = 0

    fun copyStudent(name: String = this.name, age: Int = this.age): Student {
        val student = Student(name)
        student.age = age
        return student
    }
}





