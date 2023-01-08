package com.example.softwarequiztime.controller

import com.example.softwarequiztime.MainActivity
import com.example.softwarequiztime.Play

class MainController(var view: MainActivity) {
    fun newGame() {
        view.startNewGame(Play::class.java)
    }
}