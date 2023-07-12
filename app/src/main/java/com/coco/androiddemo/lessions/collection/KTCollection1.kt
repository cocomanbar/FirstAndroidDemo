package com.coco.androiddemo.lessions.collection

fun main() {

    // list
    val list1 = listOf<Int>(1, 2, 3)

    val list2 = MutableList(5, init = {
        it * 2
    })
    list2.add(2)
    println("list2.count = ${list2.count()}")

    val list3 = mutableListOf<Int>()

    val list4 = emptyList<String>()


    // map to 符号创建了一个短时存活的 Pair 对象，因此建议仅在性能不重要时才使用它
    val map1 = mapOf<String, Int>("key1" to 1, "key2" to 2)
    val map2 = mapOf("key1" to 1, "key2" to "aa")

    val map3 = mutableMapOf<String, String>()
    var map4 = mutableMapOf<String, Any>()

    map3.put("2", "2")
    map3.put(key = "11", value = "222")
    map3.remove("2")
    map3.clear()

    val map5 = emptyMap<String, String>()

}





























