package com.coco.androiddemo.ui.study

import android.app.DownloadManager.Request
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.coco.androiddemo.R
import com.coco.androiddemo.network.ApiServer
import com.coco.androiddemo.network.ApiStudy
import com.coco.androiddemo.network.HiRetrofit
import kotlinx.android.synthetic.main.fragment_study.recycler_view
import kotlinx.android.synthetic.main.item_fragemnet_study.view.item_course_button
import kotlinx.android.synthetic.main.item_fragemnet_study.view.item_course_detail
import kotlinx.android.synthetic.main.item_fragemnet_study.view.item_course_more
import kotlinx.android.synthetic.main.item_fragemnet_study.view.item_course_title
import kotlinx.android.synthetic.main.item_fragemnet_study.view.item_image
import kotlinx.android.synthetic.main.lessionlist_item_grid.view.item_title
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudyFragment: Fragment(R.layout.fragment_study) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(context)

        val adapter: StudyAdapter = StudyAdapter()
        recycler_view.adapter = adapter

        HiRetrofit.create(ApiStudy::class.java)
            .getStudyList().enqueue(object : Callback<List<StudyCourse>>{
                override fun onFailure(call: Call<List<StudyCourse>>, t: Throwable) {
                    Log.e("StudyFragment", t.message?:"")
                }
                override fun onResponse(call: Call<List<StudyCourse>>,  response: Response<List<StudyCourse>>) {
                    Log.e("StudyFragment", response.body().toString())
                    adapter.setDatas(response.body())
                }
            })

    }

    inner class StudyAdapter : RecyclerView.Adapter<StudyAdapter.StudyViewHolder>() {

        private val course = mutableListOf<StudyCourse>()

        fun setDatas(datas: List<StudyCourse>?) {
            datas?.let {
                if (it.isNotEmpty()) {
                    course.addAll(it)
                    // 刷新
                    notifyDataSetChanged()
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder {
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_fragemnet_study, parent, false)
            return StudyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return course.size
        }

        override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {

            val course = this.course[position]

            // 加载图片与绑定
            val option = RequestOptions()
                .transform(RoundedCorners(15))
                .placeholder(R.drawable.avatar)
            Glide.with(context!!).load(course.poster).apply(option).into(holder.itemView.item_image)
            // 大标题
            holder.itemView.item_course_title.text = course.title
            // 标签
            holder.itemView.item_course_button.text = course.label
            // 进度
            holder.itemView.item_course_detail.text = course.progress
            // 更多icon
            holder.itemView.item_course_more.setImageResource(R.drawable.icon_studymore)
        }

        inner class StudyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        }
    }

}