package com.coco.androiddemo.ui.study

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coco.androiddemo.R
import kotlinx.android.synthetic.main.fragment_study.recycler_view
import kotlinx.android.synthetic.main.item_fragemnet_study.view.item_course_button
import kotlinx.android.synthetic.main.item_fragemnet_study.view.item_course_detail
import kotlinx.android.synthetic.main.item_fragemnet_study.view.item_course_more
import kotlinx.android.synthetic.main.item_fragemnet_study.view.item_course_title
import kotlinx.android.synthetic.main.item_fragemnet_study.view.item_image
import kotlinx.android.synthetic.main.lessionlist_item_grid.view.item_title

class StudyFragment: Fragment(R.layout.fragment_study) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(context)

        recycler_view.adapter = StudyAdapter()
    }

    inner class StudyAdapter : RecyclerView.Adapter<StudyAdapter.StudyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder {
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_fragemnet_study, parent, false)
            return StudyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {
            // 图片
            holder.itemView.item_image.setImageResource(R.drawable.icon_study_image)
            // 大标题
            holder.itemView.item_course_title.text = "架构师体系课程[$position]"
            // 标签
            holder.itemView.item_course_button.text = "架构师"
            // 进度
            holder.itemView.item_course_detail.text = "已学 .60%"
            // 更多icon
            holder.itemView.item_course_more.setImageResource(R.drawable.icon_studymore)
        }

        inner class StudyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        }
    }

}