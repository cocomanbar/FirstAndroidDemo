package com.coco.androiddemo.lessions.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class TestBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        val action = p1?.action ?: return
        if (action == ConnectivityManager.CONNECTIVITY_ACTION) {
            val connectivityManager: ConnectivityManager = p0?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivityManager.activeNetworkInfo
            if (info != null && info.isAvailable) {
                // 网络可达
                Toast.makeText(p0, "\"网络可达 name: ${info.typeName}", Toast.LENGTH_LONG).show()
            } else {
                // 网络不可达
                Toast.makeText(p0, "网络不可达", Toast.LENGTH_LONG).show()
            }
        } else if (action == "com.coco.androiddemo.TEST_BROADCAST_RECEIVER") {
            Toast.makeText(p0, "自定义数据来了", Toast.LENGTH_LONG).show()
        }
    }
}