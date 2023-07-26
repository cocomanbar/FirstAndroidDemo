package com.coco.androiddemo.lessions.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ThreeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 重点
        // 第一次创建时进行调用，在这个方法里通常做Activity的初始化工作，例如：加载布局，绑定事件
        // 这里加载布局：`setContentView(R.layout.activity_main)`
        // 绑定事件：navView.setupWithNavController(navController)
        Log.e("Activity-Three", "onCreate")


        // 获取从之前的Activity传递的参数
        val value1 = intent.getStringExtra("key1")
        val value2 = intent.getIntExtra("key2", 0)

        val textView = TextView(this)
        textView.text = "ThreeActivity Params：$value1, $value2"
        textView.gravity = Gravity.CENTER_VERTICAL
        setContentView(textView)

        textView.setOnClickListener {

            val resultIntent = Intent()
            resultIntent.putExtra("key3", "点击回来了123")
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // 关闭
        }

    }

    override fun onStart() {
        super.onStart()

        // 这个方法会在Activity由不可见变为可见的时候调用
        // 但是还不能和用户进行交互
        Log.e("Activity-Three", "onStart")
    }

    override fun onResume() {
        super.onResume()

        // 重点
        // 表示Activity启动完成，进入了前台
        // 可以和用户进行交互了
        Log.e("Activity-Three", "onResume")
    }

    override fun onPause() {
        super.onPause()

        // 重点
        // 这个方法是在系统准备去启动另一个Activity的时候调用，或结束当前的Activity的时候调用
        // 可以在这里释放系统资源，动画停止，不适合做耗时操作
        Log.e("Activity-Three", "onPause")
    }

    override fun onStop() {
        super.onStop()

        // 当Activity不可见的时候会回调次方法。
        // 需要释放全部用户使用不到的资源，可以做重量级的工作，例如对注册广播的解注册，对一些状态数据的存储。
        // 此时Activity还不会立马被销毁，而是在内存中，但随时都会被回收。
        // 通常发生在启动另一个Activity或切换到后台的时候
        Log.e("Activity-Three", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        // 重点
        // 即将被销毁，此时必须主动释放所有占用资源
        Log.e("Activity-Three", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()

        // 这个方法在Activity由停止状态转变为运行状态之前调用，也就是Activity被重启动了
        // APP切换到后台onStop，再切换到前台onRestart -> onStart -> onResume
        Log.e("Activity-Three", "onRestart")
    }

}