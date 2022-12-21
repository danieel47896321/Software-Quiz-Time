package com.example.softwarequiztime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var playButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        playButton = findViewById(R.id.playButton)
        setPlayButton()
    }
    private fun setPlayButton(){
        playButton.setOnClickListener{
            startActivity(Intent(this,Play::class.java))
            finish()
        }
    }
}