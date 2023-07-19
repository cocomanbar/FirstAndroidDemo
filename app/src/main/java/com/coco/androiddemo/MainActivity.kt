package com.coco.androiddemo

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.coco.androiddemo.databinding.ActivityMainBinding
import com.coco.androiddemo.network.HiOkHttp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        // 测试网络请求
        testNetwork()
    }

    private fun testNetwork() {

        // HiOkHttp.networkAsyncGet("user/query?userId=1600932269")

        // HiOkHttp.networkAsyncPost("tag/toggleTagFollow", mapOf("a" to "b"))

        // HiOkHttp.networkAsyncPostJSONString()

        HiOkHttp.networkAsyncPostString()
    }
}