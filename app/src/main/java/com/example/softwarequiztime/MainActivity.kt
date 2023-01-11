package com.example.softwarequiztime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.softwarequiztime.controller.MainController

class MainActivity : AppCompatActivity() {
    private lateinit var playButton : Button
    private lateinit var mainController: MainController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        mainController = MainController(this)
        playButton = findViewById(R.id.playButton)
        setPlayButton()
    }
    private fun setPlayButton(){
        playButton.setOnClickListener{
            startNewGame(mainController.newGameDest())
        }
    }
    private fun startNewGame(dest: Class<Play>) {
        startActivity(Intent(this, dest))
        finish()
    }
}