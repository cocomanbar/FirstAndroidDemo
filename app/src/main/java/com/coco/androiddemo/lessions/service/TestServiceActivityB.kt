package com.coco.androiddemo.lessions.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.coco.androiddemo.R
import kotlinx.android.synthetic.main.activity_test_service_2.bind
import kotlinx.android.synthetic.main.activity_test_service_2.getCount
import kotlinx.android.synthetic.main.activity_test_service_2.unbind
import android.content.ServiceConnection as ServiceConnection

class TestServiceActivityB: AppCompatActivity() {

    private var myBinder: TestServiceB.MyBinder? = null

    private var connection: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_service_2)

        // 通过binder方式打开，需要一个connection对象
        connection = object : ServiceConnection {

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {

                myBinder = p1 as TestServiceB.MyBinder

                println("成功连接...testServiceB = ${myBinder.toString() ?: "空"}")
            }

            override fun onServiceDisconnected(p0: ComponentName?) {

                println("断开连接...testServiceB")
            }

        }

        // 强行解绑 !!
        bind.setOnClickListener {
            val intent = Intent(this, TestServiceB::class.java)
            connection?.let {
                bindService(intent, it, Context.BIND_AUTO_CREATE)
            }
        }

        unbind.setOnClickListener {
            connection?.let {
                unbindService(it)
            }
        }

        getCount.setOnClickListener {

            val count = myBinder?.getCount()
            println("count = $count")
        }

    }


    // 需要在这里销毁，因为bind生命周期和调用方一致
    // 除非手动管理释放
    override fun onDestroy() {
        super.onDestroy()

        connection?.let {
            unbindService(it)
        }

    }
}