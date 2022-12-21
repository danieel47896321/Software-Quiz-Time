package com.example.softwarequiztime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Play : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        init()
    }
    private fun init(){

    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}