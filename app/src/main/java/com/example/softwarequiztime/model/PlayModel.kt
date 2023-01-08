package com.example.softwarequiztime.model

import androidx.lifecycle.ViewModel
import com.example.softwarequiztime.myClass.MyQuestion

class PlayModel: ViewModel() {
    var questionListArray: ArrayList<MyQuestion> = ArrayList<MyQuestion>()
    var points: Int = 0
    var questionNumber: Int = 0
}
