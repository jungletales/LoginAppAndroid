package com.nupura.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val userName = intent.getStringExtra("user_id")
    val name = findViewById<TextView>(R.id.title)

    name.text = "Welcome "
    }
}