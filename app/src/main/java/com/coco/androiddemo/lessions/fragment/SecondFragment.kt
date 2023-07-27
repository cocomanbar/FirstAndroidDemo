package com.coco.androiddemo.lessions.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

class SecondFragment: Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 当Activity与Fragment发生关联时调用
        Log.e("Fragment-Second", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("Fragment-Second", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 创建该Fragment的视图
        Log.e("Fragment-Second", "onCreateView")

        val textView = TextView(inflater.context)
        textView.text = "SecondFragment"
        textView.gravity = Gravity.CENTER
        textView.setOnClickListener {

        }
        return textView
    }

    // 取值一般在这
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val value1 = arguments?.getString("key1")
        val value2 = arguments?.getInt("key2")

        val textView = view as TextView
        textView.text = "SecondFragment: $value1, $value2"
    }

    override fun onStart() {
        super.onStart()

        // 可见不可交互
        Log.e("Fragment-Second", "onStart")
    }

    override fun onResume() {
        super.onResume()

        // 可见可交互
        Log.e("Fragment-Second", "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.e("Fragment-Second", "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.e("Fragment-Second", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // 与onCreateView相对应，当该Fragment被移除时调用
        Log.e("Fragment-Second", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()

        // Fragment 对象销毁时执行
        Log.e("Fragment-Second", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()

        // 与onAttach()相对应，当Fragment与Activity的关联被取消时调用
        Log.e("Fragment-Second", "onDetach")
    }
}