package com.example.softwarequiztime

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.softwarequiztime.controller.PlayController

class Play : AppCompatActivity() {
    private lateinit var playController: PlayController
    private lateinit var points : TextView
    private lateinit var question : TextView
    private lateinit var textViewAnswer1 : TextView
    private lateinit var textViewAnswer2 : TextView
    private lateinit var textViewAnswer3 : TextView
    private lateinit var textViewAnswer4 : TextView
    private lateinit var cardViewAnswer1 : CardView
    private lateinit var cardViewAnswer2 : CardView
    private lateinit var cardViewAnswer3 : CardView
    private lateinit var cardViewAnswer4 : CardView
    private lateinit var image : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        init()
    }
    private fun init(){
        playController = PlayController(this)
        points = findViewById(R.id.points)
        question = findViewById(R.id.question)
        textViewAnswer1 = findViewById(R.id.textViewAnswer1)
        textViewAnswer2 = findViewById(R.id.textViewAnswer2)
        textViewAnswer3 = findViewById(R.id.textViewAnswer3)
        textViewAnswer4 = findViewById(R.id.textViewAnswer4)
        cardViewAnswer1 = findViewById(R.id.cardViewAnswer1)
        cardViewAnswer2 = findViewById(R.id.cardViewAnswer2)
        cardViewAnswer3 = findViewById(R.id.cardViewAnswer3)
        cardViewAnswer4 = findViewById(R.id.cardViewAnswer4)
        image = findViewById(R.id.image)
        playController.setView()
        setAnswers()
    }
    fun mainWhenCase(id: String){
        when(id){
            "setQuestion" -> { setQuestion(playController.getCurrentPoints(), playController.getQuestionNumber(), playController.getAnswer1(), playController.getAnswer2(), playController.getAnswer3(), playController.getAnswer4(), playController.getCurrentImage()) }
            "engGameMsg" -> { engGameMsg(playController.getEndGameTitle(), playController.getEndGameText()) }
        }
    }
    private fun setAnswers() {
        cardViewAnswer1.setOnClickListener {
            val color = playController.checkAnswer(textViewAnswer1.text.toString())
            selectedAnswer(textViewAnswer1, color)
        }
        cardViewAnswer2.setOnClickListener {
            val color = playController.checkAnswer(textViewAnswer2.text.toString())
            selectedAnswer(textViewAnswer2, color)
        }
        cardViewAnswer3.setOnClickListener {
            val color = playController.checkAnswer(textViewAnswer3.text.toString())
            selectedAnswer(textViewAnswer3, color)
        }
        cardViewAnswer4.setOnClickListener {
            val color = playController.checkAnswer(textViewAnswer4.text.toString())
            selectedAnswer(textViewAnswer4, color)
        }
    }
    private fun selectedAnswer(textView: TextView, color: Int){
        if(playController.checkColor(color)) {
            textView.setBackgroundColor(color)
            playController.nextQuestion()
        }
    }
    private fun setQuestion(currentPoints: String, questionNumber: String, answer1: String, answer2: String, answer3: String, answer4: String, currentImage: Int) {
        points.text = currentPoints
        question.text = questionNumber
        image.setImageDrawable(ContextCompat.getDrawable(this,currentImage))
        setAnswer(textViewAnswer1, answer1)
        setAnswer(textViewAnswer2, answer2)
        setAnswer(textViewAnswer3, answer3)
        setAnswer(textViewAnswer4, answer4)
    }
    private fun setAnswer(textView: TextView, answer: String){
        textView.text = answer
        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
    }
    private fun engGameMsg(title: String, message: String) {
        val builder = AlertDialog.Builder(this, R.style.AlertDialog)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.OK) { _, _ -> onBackPressed() }
        builder.setCancelable(false)
        builder.show()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}