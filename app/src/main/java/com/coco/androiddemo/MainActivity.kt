package com.coco.androiddemo

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.coco.androiddemo.databinding.ActivityMainBinding
import com.coco.androiddemo.network.ApiServer
import com.coco.androiddemo.network.HiOkHttp
import com.coco.androiddemo.network.HiRetrofit
import com.coco.androiddemo.network.UserJson
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        // 测试网络请求
//        testHiHttp()

        testRetrofit()

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