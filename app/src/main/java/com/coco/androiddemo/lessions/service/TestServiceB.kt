package com.coco.androiddemo.lessions.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class TestServiceB: Service() {

    private val testTag = "Service-B"

    private val binder: Binder = MyBinder()

    private var count: Int = 0

    private var quick: Boolean = true

    inner class MyBinder: Binder() {
        // 定义方法，用于service 与 activity 交互
        fun getCount(): Int {
            return count
        }
    }

    override fun onCreate() {
        super.onCreate()

        Log.e(testTag, "onCreate")

        Thread(Runnable {
            while (quick) {
                Thread.sleep(1000)
                count ++
            }
        }).start()

    }

    override fun onBind(p0: Intent?): IBinder? {

        Log.e(testTag, "onBind")

        // 返回这个binder
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {

        Log.e(testTag, "onUnbind")

        return super.onUnbind(intent)
    }


    // 通过 binder启动的服务，这个方法是不会走的
    // 这个时候，业务代码可以写在create或bind里
    // 他的生命周期和调用方一样，需要在调用的销毁时，销毁绑定
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.e(testTag, "onStartCommand")

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e(testTag, "onDestroy")

        quick = false
    }
}