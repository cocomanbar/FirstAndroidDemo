package com.coco.androiddemo.network

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit
import com.coco.androiddemo.ui.study.StudyCourse

// RESTFUL网络请求框架Retrofit
// https://github.com/square/retrofit

object HiRetrofit {

    //  http://123.56.232.18:8080/serverdemo/
    private const val base_url: String = "http://10.0.2.2:4523/m1/3058746-0-default/"

    private  val httpLoggingInterceptor = LoggingInterceptor()

    private val client = OkHttpClient.Builder()  // 构造器
    .connectTimeout(15, TimeUnit.SECONDS)   // 连接超时
    .readTimeout(15, TimeUnit.SECONDS)      // 读取超时
    .writeTimeout(15, TimeUnit.SECONDS)     // 写超时
    .addInterceptor(httpLoggingInterceptor)         // 网络监听
    .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("$base_url")
        .addConverterFactory(GsonConverterFactory.create())     //数据转换器
        .build()

    fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}

interface ApiStudy {

    @GET(value = "study_list")
    fun getStudyList() : Call<List<StudyCourse>>


}

interface ApiServer {

    @GET(value = "user/query")
    fun queryUser(@Query(value = "userId", encoded = true) userId: String): Call<UserJson>

    // 使用@Headers添加多个请求头
    @Headers("{ \"X-Foo: Bar\", \"X-Ping: Pong\" }")
    @GET(value = "user/query")
    fun queryUser1(@Query(value = "suerId", encoded = true) userId: String): Call<UserJson>

    // 多个参数的情况下可以使用@QueryMap
    @GET(value = "user/query")
    fun queryUser2(@QueryMap map: Map<String, String>): Call<UserJson>

    /*
    *   很多情况下，需要上传json格式，而且数据很多，还会变化，这个时候可以使用 @Body
    *   字符串提交
    */
    @POST(value = "user/update")
    fun userUpdate(@Body body: RequestBody)

    // 表单提交
    @POST(value = "user/update")
    @FormUrlEncoded
    fun userUpdate1(@FieldMap map: Map<String, Object>): Call<Unit>

    // 表单上传文件（键值对提交，同时上传文件）
    @POST(value = "upload/upload")
    @Multipart
    fun upload(@Part file: RequestBody, @Query(value = "openId", encoded = true) openId: String, @PartMap map: Map<String, RequestBody>): Call<Unit>



}

