package com.example.softwarequiztime.controller

import android.os.Handler
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.softwarequiztime.myClass.MyQuestion
import com.example.softwarequiztime.Play
import com.example.softwarequiztime.R
import com.example.softwarequiztime.model.PlayModel

class PlayController(var view: Play) {
    private var playModel = ViewModelProvider(view)[PlayModel::class.java]
    fun setView() {
        if(playModel.questionListArray.isEmpty())
            setQuestions()
        view.mainWhenCase(playModel.questionsID)
    }
    fun getAnswer1(): String{ return playModel.questionListArray[playModel.questionNumber].answer1 }
    fun getAnswer2(): String{ return playModel.questionListArray[playModel.questionNumber].answer2 }
    fun getAnswer3(): String{ return playModel.questionListArray[playModel.questionNumber].answer3 }
    fun getAnswer4(): String{ return playModel.questionListArray[playModel.questionNumber].answer4 }
    fun getCurrentPoints(): String{ return "${playModel.points} ${view.resources.getString(R.string.Points)}" }
    fun getQuestionNumber(): String{ return "${playModel.questionNumber+1}${view.resources.getString(R.string.Of5)}" }
    fun getCurrentImage(): Int{ return playModel.questionListArray[playModel.questionNumber].image }
    private fun setQuestions() {
        playModel.questionListArray.add(MyQuestion(R.drawable.android, view.resources.getString(R.string.Android), view.resources.getString(R.string.Apple), view.resources.getString(R.string.Asus), view.resources.getString(R.string.Nokia), view.resources.getString(R.string.Android)))
        playModel.questionListArray.add(MyQuestion(R.drawable.apple, view.resources.getString(R.string.Windows), view.resources.getString(R.string.Android), view.resources.getString(R.string.Apple), view.resources.getString(R.string.Microsoft), view.resources.getString(R.string.Apple)))
        playModel.questionListArray.add(MyQuestion(R.drawable.java, view.resources.getString(R.string.Java), view.resources.getString(R.string.Python), view.resources.getString(R.string.Cplusplus), view.resources.getString(R.string.Kotlin), view.resources.getString(R.string.Java)))
        playModel.questionListArray.add(MyQuestion(R.drawable.kotlin, view.resources.getString(R.string.Python), view.resources.getString(R.string.C), view.resources.getString(R.string.Cplusplus), view.resources.getString(R.string.Kotlin), view.resources.getString(R.string.Kotlin)))
        playModel.questionListArray.add(MyQuestion(R.drawable.python, view.resources.getString(R.string.Kotlin), view.resources.getString(R.string.Python), view.resources.getString(R.string.Java), view.resources.getString(R.string.Cplusplus), view.resources.getString(R.string.Python)))
    }
    fun checkAnswer(answer: String): Int {
        if(!playModel.questionListArray[playModel.questionNumber].isClicked) {
            playModel.questionListArray[playModel.questionNumber].isClicked = true
            return if (answer == playModel.questionListArray[playModel.questionNumber].correctAnswer) {
                playModel.points += 10
                ContextCompat.getColor(view, R.color.green)
            } else {
                ContextCompat.getColor(view, R.color.red)
            }
        }
        return -1
    }
    fun checkColor(color: Int): Boolean{ return color != -1 }
    fun nextQuestion() {
        if(playModel.questionListArray[playModel.questionNumber].isClicked) {
            Handler().postDelayed({
                if(playModel.questionNumber == playModel.questionListArray.size - 1) {
                    setView()
                    view.mainWhenCase(playModel.endGameID)
                    resetGame()
                }else {
                    playModel.questionNumber++
                    setView()
                }
            }, 750)
        }
    }
    private fun resetGame() {
        playModel.points = 0
        playModel.questionNumber = 0
        playModel.questionListArray.clear()
    }
    fun getEndGameTitle(): String{ return view.resources.getString(R.string.Finish) }
    fun getEndGameText(): String{ return "${view.resources.getString(R.string.GameOver)} ${playModel.points}" }
}