package com.coco.androiddemo.lessions.activity

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.coco.androiddemo.R
import com.google.android.material.bottomnavigation.BottomNavigationView

// 如何使用这个`SecondActivity`，需要AndroidManifest.xml注册
class SecondActivity: AppCompatActivity() {

    // 重写一个参数的 onCreate()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 重点
        // 第一次创建时进行调用，在这个方法里通常做Activity的初始化工作，例如：加载布局，绑定事件
        Log.e("SecondActivity", "onCreate")

        val textView = TextView(this)
        textView.text = "SecondActivity"
        textView.gravity = Gravity.CENTER_VERTICAL
        setContentView(textView)

    }

    override fun onStart() {
        super.onStart()

        // 这个方法会在Activity由不可见变为可见的时候调用
        // 但是还不能和用户进行交互
        Log.e("SecondActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()

        // 重点
        // 表示Activity启动完成，进入了前台
        // 可以和用户进行交互了
        Log.e("SecondActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()

        // 重点
        // 这个方法是在系统准备去启动另一个Activity的时候调用，或结束当前的Activity的时候调用
        // 可以在这里释放系统资源，动画停止，不适合做耗时操作
        Log.e("SecondActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()

        // 当Activity不可见的时候会回调次方法。
        // 需要释放全部用户使用不到的资源，可以做重量级的工作，例如对注册广播的解注册，对一些状态数据的存储。
        // 此时Activity还不会立马被销毁，而是在内存中，但随时都会被回收。
        // 通常发生在启动另一个Activity或切换到后台的时候
        Log.e("SecondActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        // 重点
        // 即将被销毁，此时必须主动释放所有占用资源
        Log.e("SecondActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()

        // 这个方法在Activity由停止状态转变为运行状态之前调用，也就是Activity被重启动了
        // APP切换到后台onStop，再切换到前台onRestart -> onStart -> onResume
        Log.e("SecondActivity", "onRestart")
    }
}