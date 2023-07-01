package com.coco.androiddemo.ui.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.coco.androiddemo.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.lessionlist_item_linear_vertical.view.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 不需要通过find xxx，引入插件后，直接通过id 查找对应的view模块


        // HORIZONTAL -> R.layout.lessionlist_item_linear_horizontal
        recycler_view_example_vertical.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // VERTICAL -> R.layout.lessionlist_item_linear_vertical
        // recycler_view_example_vertical.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        recycler_view_example_vertical.adapter = MyAdapter()
    }

    // inner 修饰内部类，可以使用内部参数
    // 定义一个 Adapter
    inner class MyAdapter: RecyclerView.Adapter<MyViewHolder>() {

        // 返回布局信息
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

            val itemView: View = LayoutInflater.from(context)
                .inflate(R.layout.lessionlist_item_linear_horizontal, parent, false)
            return MyViewHolder(itemView)
        }

        // 列表数量
        override fun getItemCount(): Int {
            return 20
        }

        // 数据绑定
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            // 设置本地图片的几种方法
            // holder.itemView.item_imageView.setImageResource(R.drawable.ic_home_black_24dp)
            // holder.itemView.item_imageView.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_home_black_24dp))
            holder.itemView.item_imageView.setImageBitmap(BitmapFactory.decodeResource(context!!.resources, R.drawable.avatar))

            // 设置文本
            holder.itemView.item_title.text = "【${position}】移动架构师课程"
            // 副标题
            holder.itemView.item_message.text = "深耕移动端领域7年有余，架构及技术团队管理经验丰富，擅长Android 、Java、Weex、JSBridge"
        }

    }

    // 定义一个 ViewHolder
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

}