package com.example.dlgdesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_account_create.setOnClickListener {
            accountCreate()
        }

    }

    private fun accountCreate() {
        intent = Intent(this,CreateAccount::class.java)

        startActivity(intent)
    }
}