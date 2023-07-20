package com.coco.androiddemo.network
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun main() {

    // 单个对象和json字符串转换
//    single()

    // 多个
    mutable()
}

fun mutable() {

    // json字符串转为集合对象
    val json = "[{\"uid\":\"0001\", \"userName\":\"小米\", \"passWord\":\"12580\", \"telNumber\":\"13055588855\"}," +
            "{\"uid\":\"0002\", \"userName\":\"小米2\", \"passWord\":\"12580\", \"telNumber\":\"13055588855\"}]"
    val gson = Gson()
    // 泛型List<Account>
    val accountList: List<Account> = gson.fromJson(json, object: TypeToken<List<Account>>(){}.type)
    println("account = ${accountList.size}")

    // 对象集合转为json字符串
    val accountString: String = gson.toJson(accountList)
    println("account string = $accountString")
}

fun single() {

    // json字符串转为对象
    val json = "{\"uid\":\"0001\", \"userName\":\"小米\", \"passWord\":\"12580\", \"telNumber\":\"13055588855\"}"
    val gson = Gson()
    val account: Account = gson.fromJson(json, Account::class.java)
    println("account = ${account.toString()}")

    // 对象转为json字符串
    val accountString: String = gson.toJson(account)
    println("account string = $accountString")
}

class Account {

    var uid: String = ""
    var userName: String = ""
    var passWord: String = ""
    var telNumber: String = ""

    // 如何快速生成 toString()
    // 全选属性，选择格式生成，全选？或所需，确定。
    override fun toString(): String {
        return "Account(uid='$uid', userName='$userName', passWord='$passWord', telNumber='$telNumber')"
    }
}

data class Account2(
    val uid: String,
    var userName: String,
    var passWord: String,
    var telNumber: String
)

