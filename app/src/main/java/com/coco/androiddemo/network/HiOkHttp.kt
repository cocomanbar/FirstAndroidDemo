package com.coco.androiddemo.network

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.IOException
import java.io.File
import java.util.concurrent.TimeUnit

object HiOkHttp {

    private const val base_url: String = "http://123.56.232.18:8080/serverdemo"

    private val  client: OkHttpClient

    init {

        // 自带的，会有多余的信息打印
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        // LoggingInterceptor()
        val httpLoggingInterceptor = LoggingInterceptor()

        client = OkHttpClient.Builder()  // 构造器
            .connectTimeout(15, TimeUnit.SECONDS)   // 连接超时
            .readTimeout(15, TimeUnit.SECONDS)      // 读取超时
            .writeTimeout(15, TimeUnit.SECONDS)     // 写超时
            .addInterceptor(httpLoggingInterceptor)         // 网络监听
            .build()
    }

    // android 分为主线程、子线程
    // 主线程就是app一起动，android framework 层会启动一个线程
    //  主线程ui
    // 子线程 -- new Thread().start()

    // 同步请求步骤：
    // 1.创建OKHttpClient实例
    // 2.Request.Build 构建一个request请求实例
    // 3.通过client对象newCall请求实例创建一个call对象
    // 4.Call调用execute方法发送同步请求
    // 5.请求返回的response转换为String类型返回

    // 同步请求GET，主线程使用同步请求，会报错
    fun networkSyncGet(url: String) {
        val request: Request = Request.Builder()
            .url("$base_url/$url")
            .build()

        // 构造请求对象
        val call: Call = client.newCall(request)
        // 发起同步请求
        val response: Response = call.execute()
        // 请求结果
        val body: String? = response.body?.string()
        // 打印看看
        Log.e("OKHttp", "get response: $body")
    }

    // 异步请求步骤：
    // 1.创建OKHttpClient实例
    // 2.Request.Build 构建一个request请求实例
    // 3.通过client对象newCall请求实例创建一个call对象
    // 4.Call调用enqueue方法发送请求，得到一个子线程回调
    // 5.处理ui需要回到主线程

    // 异步请求GET
    fun networkAsyncGet(url: String) {
        val request: Request = Request.Builder()
            .url("$base_url/$url")
            .build()

        // 构造请求对象
        val call: Call = client.newCall(request)
        // 发起异步请求
        call.enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {
                // 打印看看
                Log.e("OKHttp", "get failed response: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                // 打印看看
                val body = response.body?.string()
                Log.e("OKHttp", "get succeed response: $body")
            }
        })
    }

    // POST请求表单请求异步
    fun networkAsyncPost(url: String, map: Map<String, String>) {

        val body: FormBody = FormBody.Builder()
            .add("userId", "1600932269")
            .add("tagId", "71")
            .build()

        val request: Request = Request.Builder()
        .url("$base_url/$url")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                // 打印看看
                Log.e("OKHttp", "post failed response: ${e.message}")
            }
            override fun onResponse(call: Call, response: Response) {
                // 打印看看
                val body = response.body?.string()
                Log.e("OKHttp", "post succeed response: $body")
            }
        })
    }

    // POST请求表单请求异步-多表单
    // 接口是支持文件上传才可以用
    fun networkAsyncPostMutablePart(context: Context, url: String, map: Map<String, String>) {

        // 在android6.0以及之后，读取外部存储卡的文件都是需要动态申请权限（会有弹框），声明了也是需要的
        // 这里不完善的
        val file = File(Environment.getExternalStorageDirectory(), "1.png")
        if (!file.exists()) {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show()
            return
        }

        val body = MultipartBody.Builder()
            .addFormDataPart("key1", "value1")
            .addFormDataPart("key2", "value2")
            .addFormDataPart("file", "filename", body = RequestBody.create("application/octet-stream".toMediaType(), file))
            .build()

        val request: Request = Request.Builder()
            .url("$base_url/$url")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                // 打印看看
                Log.e("OKHttp", "post mutablePart failed response: ${e.message}")
            }
            override fun onResponse(call: Call, response: Response) {
                // 打印看看
                val body = response.body?.string()
                Log.e("OKHttp", "post mutablePart succeed response: $body")
            }
        })
    }

    // 提交字符串，可以是json字符串 ->application/json
    // 或纯文本字符串 -> text/plan
    fun networkAsyncPostJSONString() {

        val jsonObj = JSONObject()
        jsonObj.put("key1", "value1")
        jsonObj.put("key2", "value2")
        jsonObj.put("key3", 2)

        val body = RequestBody.create("application/json;charset=utf-8".toMediaType(), jsonObj.toString())

        val request = Request.Builder()
            .url("$base_url")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                // 打印看看
                Log.e("OKHttp", "post string failed response: ${e.message}")
            }
            override fun onResponse(call: Call, response: Response) {
                // 打印看看
                val body = response.body?.string()
                Log.e("OKHttp", "post string succeed response: $body")
            }
        })
    }

    // 提交字符串，可以是json字符串 ->application/json
    // 或纯文本字符串 -> text/plan
    fun networkAsyncPostString() {

        val jsonObj = "hello"

        val body = RequestBody.create("text/plan;charset=utf-8".toMediaType(), jsonObj)

        val request = Request.Builder()
            .url("$base_url")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                // 打印看看
                Log.e("OKHttp", "post string failed response: ${e.message}")
            }
            override fun onResponse(call: Call, response: Response) {
                // 打印看看
                val body = response.body?.string()
                Log.e("OKHttp", "post string succeed response: $body")
            }
        })
    }

}