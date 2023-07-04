package com.coco.androiddemo.lessions.kotlin_base_class

// 属性 与 字段


fun main() {

}

// 只读属性的值在编译期是已知的，那么可以使用 const 修饰符将其标记为编译期常量
// 编译期常量
const val super_key_1: String = "hahahahaah"

// var 可变的
// val 只读
class Address {

    var name: String = "HomePod"
    var street: String = "Baker"

    /*
    * var <propertyName>[: <PropertyType>] [= <property_initializer>]
        [<getter>]
        [<setter>]
    * */
    var nameString1: String
        get() = name
        set(value) {
            name = value
        }

    var nameString2: String = ""
        get() = name
        private set // 此 setter 是私有的并且有默认实现

    var nameString3: String = ""
        set(value) {
            if (value.isNotEmpty()) {
                field = value
            }
        }

    // 幕后字段
    var counter: Int = 0  // 这个初始器直接为幕后字段赋值 此时 field = 0
        set(value) {
            if (value >= 0) {
                field = value // 此时 field = value
            }
        }

    // 幕后属性（backing property）

    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap()
            }
            return _table as Map<String, Int>
        }
        // set(value) {} 打开就报错了，因为 table 是val

    // 延迟初始化
    // 初始化前访问一个 lateinit 属性会抛出一个特定异常
    lateinit var subJext: String

    // 检测一个 lateinit var 是否已经初始化过
    fun test() {
        val address = Address()
        if (address::subJext.isInitialized) {
            println("初始化过了")
        }
    }

}
