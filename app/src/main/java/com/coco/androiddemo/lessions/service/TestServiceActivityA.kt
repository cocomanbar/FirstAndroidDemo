package com.coco.androiddemo.lessions.service

import android.app.Notification
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.coco.androiddemo.R
import kotlinx.android.synthetic.main.activity_test_service_1.start_service
import kotlinx.android.synthetic.main.activity_test_service_1.stop_service

class TestServiceActivityA: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_service_1)

        // 推到后台是60s为判断
        // Context.startForegroundService() did not then call Service.startForeground()
        // 执行这个延期，如果不发送通知。就报上面的错误，当前还需要权限
        Handler().postDelayed(Runnable {
            start()
        },70*1000)

        start_service.setOnClickListener {
            start()
        }

        stop_service.setOnClickListener {
            stop()
        }
    }

    private fun start() {
        val intent = Intent(this, TestServiceA::class.java)

        // 推到后台服务依然在的做法，不然会报错，ANR
        // 绑定的时候，区分版本号
        // 8.0系统之后
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
            println("走了高版本")
        } else {
            // 按照之前的版本，直接启动
            startService(intent)
            println("走了低版本")
        }
    }

    private fun stop() {
        val intent = Intent(this, TestServiceA::class.java)
        stopService(intent)
    }
}