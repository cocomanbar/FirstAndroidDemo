package com.coco.androiddemo.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

// 自定义，OKHTTP自带是 HttpLoggingInterceptor
class LoggingInterceptor: Interceptor {

    // 网络请求拦截监听
    override fun intercept(chain: Interceptor.Chain): Response {
        val timeStart: Long = System.nanoTime()
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)

        val buffer: Buffer = Buffer()
        request.body?.writeTo(buffer)
        val bodyString = buffer.readUtf8()
        Log.e("OKHTTP", String.format("Send Request %s with params %s", request.url, bodyString))

        // 注意 读取string()，只能读取一次，业务里面再次读取会错误，所以需要返回一个Response
        val businessData: String = response.body?.string() ?: "response body null"
        val timeEnd: Long = System.nanoTime()
        Log.e("OKHTTP", String.format("Received response for %s in %.1fms >>> %s", request.url, (timeEnd-timeStart)/1e6, businessData))

        // 因为读取后，不能返回同一个response，所以需要构造一个新的
        val mediaType: MediaType? = response.body?.contentType()
        val newBody: ResponseBody = ResponseBody.create(mediaType, businessData)
        val newResponse: Response = response.newBuilder().body(newBody).build()
        return  newResponse
    }
}