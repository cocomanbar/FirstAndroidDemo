package com.coco.androiddemo.lessions.broadcast_receiver

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class TestBroadcastReceiverActivity: AppCompatActivity() {

    private lateinit var receiver: TestBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 运行时动态注册通知，推荐
        // 静态的方式，监听系统事件不推荐了。。。不安全
        // 但是可以注册自己的静态通知，估计也不推荐，感觉会把 AndroidManifest.xml搞大

        // 注册系统通知
//        receiver = TestBroadcastReceiver()
//        var intentFilter = IntentFilter()
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
//        registerReceiver(receiver, intentFilter)


        // 自定义发送广播：有应用之间的和应用之内的之分，api不一样
        receiver = TestBroadcastReceiver()
        var intentFilter = IntentFilter()
        intentFilter.addAction("com.coco.androiddemo.TEST_BROADCAST_RECEIVER")
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter)

        // 发送
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("com.coco.androiddemo.TEST_BROADCAST_RECEIVER"))
    }

    override fun onDestroy() {
        super.onDestroy()

        // 注销系统通知
        //unregisterReceiver(receiver)

        // 注销自定义通知
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
}