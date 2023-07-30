package com.coco.androiddemo.lessions.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

// 需要注册在AndroidManifest.xml
class TestServiceA: Service() {

    override fun onCreate() {
        super.onCreate()

        Log.e("Service-A", "onCreate")
    }

    override fun onBind(p0: Intent?): IBinder? {

        Log.e("Service-A", "onBind")

        return null
    }

    // 对于 使用 startService 的情况：
    // 这里方法就是编程业务代码的地方
    // 会多次回调，因为实例只会创建一次，多次start
    // 生命周期和应用周期一样长，除非主动关闭
    // 用于创建一个长时间持续任务的功能时候需要这样子
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.e("Service-A", "onStartCommand")

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e("Service-A", "onDestroy")
    }
}