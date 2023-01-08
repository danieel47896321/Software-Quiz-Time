package com.example.softwarequiztime

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.softwarequiztime.controller.PlayController
import com.example.softwarequiztime.model.PlayModel

class Play : AppCompatActivity() {
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
    private lateinit var playModel: PlayModel
    private lateinit var playController: PlayController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        init()
    }
    private fun init(){
        playModel = ViewModelProvider(this)[PlayModel::class.java]
        playController = PlayController(playModel, this)
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
    fun setQuestion(currentPoints: String, questionNumber: String, answer1: String, answer2: String, answer3: String, answer4: String, currentImage: Int) {
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
    fun engGameMsg(title: String, message: String) {
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