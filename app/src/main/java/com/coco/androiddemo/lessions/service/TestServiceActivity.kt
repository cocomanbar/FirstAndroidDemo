package com.coco.androiddemo.lessions.service

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coco.androiddemo.R
import kotlinx.android.synthetic.main.activity_test_service.start_service
import kotlinx.android.synthetic.main.activity_test_service.stop_service

class TestServiceActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_service)

        start_service.setOnClickListener {
            val intent = Intent(this, TestServiceA::class.java)
            startService(intent)
        }

        stop_service.setOnClickListener {
            val intent = Intent(this, TestServiceA::class.java)
            stopService(intent)
        }
    }
}