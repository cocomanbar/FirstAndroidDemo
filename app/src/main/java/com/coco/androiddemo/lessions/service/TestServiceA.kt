package com.coco.androiddemo.lessions.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.content.getSystemService

// 需要注册在AndroidManifest.xml
class TestServiceA: Service() {

    override fun onCreate() {
        super.onCreate()

        Log.e("Service-A", "onCreate")

        // 发送一个通知
        // Android 8.0 以上不能用空的通知，必须自己创建通知通道
        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel("channel_id", "haha", NotificationManager.IMPORTANCE_NONE)
            channel.lightColor = Color.BLUE
            channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

            val notification = Notification.Builder(applicationContext, "channel_id").build()

            // 设置 ID 为 0 , 就不显示已通知了 , 但是 oom_adj 值会变成后台进程 11
            // 设置 ID 为 1 , 会在通知栏显示该前台服务
            startForeground(1, notification)
            println("高版本")
        } else {
            println("低版本")
        }
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