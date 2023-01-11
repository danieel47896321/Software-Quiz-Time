package com.example.softwarequiztime.controller

import com.example.softwarequiztime.MainActivity
import com.example.softwarequiztime.Play

class MainController(var view: MainActivity) {
    fun newGameDest(): Class<Play> {
        return (Play::class.java)
    }
}