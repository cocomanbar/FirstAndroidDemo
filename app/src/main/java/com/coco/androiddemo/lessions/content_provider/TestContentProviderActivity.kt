package com.coco.androiddemo.lessions.content_provider

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
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
import kotlinx.android.synthetic.main.activity_contacts.add_contacts
import kotlinx.android.synthetic.main.activity_contacts.delete_contacts
import kotlinx.android.synthetic.main.activity_contacts.get_contacts
import kotlinx.android.synthetic.main.activity_contacts.go_back
import kotlinx.android.synthetic.main.activity_contacts.update_contacts

class TestContentProviderActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_contacts)

        // 获取读写操作
        requestPermission()

        // read
        get_contacts.setOnClickListener {
            getContacts()
        }

        // add
        add_contacts.setOnClickListener {
            addContacts()
        }

        // update
        update_contacts.setOnClickListener {
            updateContacts()
        }

        // delete
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
        val readable = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
        val writable = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED
        if (!readable || !writable) {

            // 如果已经永久拒绝了读操作
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)) {
                Toast.makeText(this, "用户已经永久拒绝读权限，跳出去", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS)
                startActivity(intent)
            }
            // 如果已经永久拒绝了写操作
            else if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_CONTACTS)) {
                Toast.makeText(this, "用户已经永久拒绝写权限，跳出去", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS)
                startActivity(intent)
            }
            // 同时获取两个权限
            else {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.WRITE_CONTACTS), 100)
            }
        } else {
            getContacts()
        }
    }

    // 类似查表的做法
    @SuppressLint("Range")
    private fun getContacts() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 区分的很详细
            // 好几个不同的表，根据需要拿不同表获取数据
            val uri1 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            val resolver1 = contentResolver
            val cursor1: Cursor = resolver1.query(uri1, null, null, null)!!
            while (cursor1.moveToNext()) {
                val id: String? = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
                val displayName: String? = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneNumber: String? = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val account = ContactAccount(name = displayName, phone = phoneNumber, address = null)
                println("id = $id, account = $account")
            }
            // 游标关闭,避免内存泄漏
            cursor1.close()

            val uri2 = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI
            val resolver2 = contentResolver
            val cursor2: Cursor = resolver2.query(uri2, null, null, null)!!
            while (cursor2.moveToNext()) {
                val id: String? = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID))
                val address: String? = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET))
                val account = ContactAccount(name = null, phone = null, address = address)
                println("id = $id, account = $account")
            }
            // 游标关闭,避免内存泄漏
            cursor2.close()
        }
    }

    private fun addContacts() {
        val resolver = contentResolver
        val values = ContentValues()
        val rawContactUri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values)!!
        val rawContactId = ContentUris.parseId(rawContactUri)

        // 姓名
        values.clear()
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "haha")
        resolver.insert(ContactsContract.Data.CONTENT_URI, values)

        // 插入手机号
        values.clear()
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "1300007885")
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
        resolver.insert(ContactsContract.Data.CONTENT_URI, values)
        
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

        if (requestCode == 100) {
            if (permissions[0] == android.Manifest.permission.READ_CONTACTS) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "读取权限获取成功", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "读取权限获取失败", Toast.LENGTH_LONG).show()
                }
            }
            if (permissions[1] == android.Manifest.permission.WRITE_CONTACTS) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "读写权限获取成功", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "读写权限获取失败", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}