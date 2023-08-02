package com.coco.androiddemo.lessions.content_provider

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.coco.androiddemo.R
import kotlinx.android.synthetic.main.activity_contacts.delete_contacts
import kotlinx.android.synthetic.main.activity_contacts.get_contacts
import kotlinx.android.synthetic.main.activity_contacts.go_back
import kotlinx.android.synthetic.main.activity_contacts.open_permission
import kotlinx.android.synthetic.main.activity_contacts.update_contacts

class TestContentProviderActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_contacts)

        open_permission.setOnClickListener {
            requestPermission()
        }

        get_contacts.setOnClickListener {
            getContacts()
        }

        update_contacts.setOnClickListener {
            updateContacts()
        }

        delete_contacts.setOnClickListener {
            deleteContacts()
        }

        go_back.setOnClickListener {
            val resultIntent = Intent()
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // 关闭
        }

    }

    // 获取权限
    private fun requestPermission() {
        // 未授权
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // 如果已经永久拒绝了
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)) {
                Toast.makeText(this, "用户已经永久拒绝权限，跳出去", Toast.LENGTH_LONG).show()

                val intent = Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS)
                startActivity(intent)

            } else {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS), 100)
            }
        } else {
            getContacts()
        }
    }

    // 类似查表的做法
    @SuppressLint("Range")
    private fun getContacts() {

        val resolver = contentResolver
        val uri = Uri.parse("content://com.android.contacts/data/phones")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val cursor: Cursor = resolver.query(uri, null, null, null)!!
            while (cursor.moveToNext()) {
                val id: String? = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
                val displayName: String? = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneNumber: String? = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                println("id = $id, name = $displayName, phone = $phoneNumber")
            }
            // 游标关闭,避免内存泄漏
            cursor.close()
        }
    }

    private fun updateContacts() {

    }

    private fun deleteContacts() {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100 && permissions[0] == android.Manifest.permission.READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts()
            } else {
                Toast.makeText(this, "权限未给到", Toast.LENGTH_LONG).show()
            }
        }
    }


}