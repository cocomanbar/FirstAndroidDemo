package com.coco.androiddemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.coco.androiddemo.lessions.service.TestServiceActivityA
import com.coco.androiddemo.network.ApiServer
import com.coco.androiddemo.network.HiOkHttp
import com.coco.androiddemo.network.HiRetrofit
import com.coco.androiddemo.network.UserJson
import retrofit2.Call
import retrofit2.Response
import com.coco.androiddemo.lessions.service.TestServiceActivityB

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_main)
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        navView.setupWithNavController(navController)


        // 测试跳转Activity行为
        val textView: TextView = TextView(this)
        textView.text = "Jump To SecondActivity."
        textView.gravity = Gravity.CENTER
        setContentView(textView)
        textView.setOnClickListener {
            // 显式普通初始化，不需要回参
            // SecondActivity
            // TestServiceActivityA
            // TestServiceActivityB
            val intent = Intent(this, TestServiceActivityA::class.java)
            startActivity(intent)
        }


        // 测试网络请求
        // testHiHttp()
        // testRetrofit()
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }

    private fun testHiHttp() {

        //  onFailure
        //  onResponse
        //  回调在子线程

         HiOkHttp.networkAsyncGet("user/query?userId=1600932269")

        // HiOkHttp.networkAsyncPost("tag/toggleTagFollow", mapOf("a" to "b"))

        // HiOkHttp.networkAsyncPostJSONString()

        // HiOkHttp.networkAsyncPostString()
    }

    private  fun testRetrofit() {

        //  onFailure
        //  onResponse
        //  回调在主线程

        val apiServer = HiRetrofit.create(ApiServer::class.java)
        apiServer.queryUser("160092369").enqueue(object : retrofit2.Callback<UserJson>{
            override fun onFailure(call: Call<UserJson>, t: Throwable) {
                Log.e("Retrofit", "${t.message ?: "onFailure"}")
            }
            override fun onResponse(call: Call<UserJson>, response: Response<UserJson>) {
                Log.e("Retrofit", response.body()?.toString() ?: "onResponse")
            }
        })
    }
}