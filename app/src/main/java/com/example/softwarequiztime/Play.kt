package com.example.softwarequiztime
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.softwarequiztime.Class.MyQuestion

class Play : AppCompatActivity() {
    private lateinit var questionListArray: ArrayList<MyQuestion>
    private var index = 0
    private var curPoints = 0
    private lateinit var points : TextView
    private lateinit var question : TextView
    private lateinit var answer1 : TextView
    private lateinit var answer2 : TextView
    private lateinit var answer3 : TextView
    private lateinit var answer4 : TextView
    private lateinit var image : ImageView
    private lateinit var answer1CardView : CardView
    private lateinit var answer2CardView : CardView
    private lateinit var answer3CardView : CardView
    private lateinit var answer4CardView : CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        init()
    }
    private fun init(){
        questionListArray = ArrayList<MyQuestion>()
        points = findViewById(R.id.points)
        question = findViewById(R.id.question)
        answer1 = findViewById(R.id.answer1)
        answer2 = findViewById(R.id.answer2)
        answer3 = findViewById(R.id.answer3)
        answer4 = findViewById(R.id.answer4)
        answer1CardView = findViewById(R.id.answer1CardView)
        answer2CardView = findViewById(R.id.answer2CardView)
        answer3CardView = findViewById(R.id.answer3CardView)
        answer4CardView = findViewById(R.id.answer4CardView)
        image = findViewById(R.id.image)
        setQuestions()
    }
    private fun setQuestions(){
        questionListArray.add(MyQuestion(R.drawable.android,"Android","Apple","Asus","Nokia","Android"))
        questionListArray.add(MyQuestion(R.drawable.apple,"Windows","Android","Apple","Microsoft","Apple"))
        questionListArray.add(MyQuestion(R.drawable.java,"Java","Python","C++","Kotlin","Java"))
        questionListArray.add(MyQuestion(R.drawable.kotlin,"Python","C","C++","Kotlin","Kotlin"))
        questionListArray.add(MyQuestion(R.drawable.python,"Kotlin","Python","Java","C","Python"))
        showQuestions()
        setButtonOnClick(answer1, answer1CardView)
        setButtonOnClick(answer2, answer2CardView)
        setButtonOnClick(answer3, answer3CardView)
        setButtonOnClick(answer4, answer4CardView)
    }
    private fun showQuestions(){
        val p = resources.getString(R.string.Points)
        val q = resources.getString(R.string.Of5)
        points.text = "$curPoints ${p}"
        question.text = "${index+1}${q}"
        image.setImageDrawable(resources.getDrawable(questionListArray[index].image))
        resetAnswerBackground(answer1)
        resetAnswerBackground(answer2)
        resetAnswerBackground(answer3)
        resetAnswerBackground(answer4)
        answer1.text = questionListArray[index].q1
        answer2.text = questionListArray[index].q2
        answer3.text = questionListArray[index].q3
        answer4.text = questionListArray[index].q4
    }
    private fun resetAnswerBackground(answer: TextView) {
        answer.setBackgroundColor(resources.getColor(R.color.answer_color))
    }
    private fun setButtonOnClick(answer: TextView, answerCardView: CardView) {
        answerCardView.setOnClickListener{
            if(!questionListArray[index].isClicked) {
                questionListArray[index].isClicked = true
                if (answer.text == questionListArray[index].correctAnswer) {
                    answer.setBackgroundColor(resources.getColor(R.color.green))
                    curPoints += 10
                    val q = resources.getString(R.string.Of5)
                    question.text = "${index+1}${q}"
                } else {
                    answer.setBackgroundColor(resources.getColor(R.color.red))
                }
                Handler().postDelayed({
                    if(index < questionListArray.size - 1) {
                        index++
                        showQuestions()
                    }else {
                        val p = resources.getString(R.string.Points)
                        points.text = "$curPoints ${p}"
                        engGameMsg()
                    }
                }, 500)
            }
        }
    }
    private fun engGameMsg() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialog)
        builder.setTitle(resources.getString(R.string.Finish))
        builder.setMessage("${resources.getString(R.string.GameOver)} $curPoints")
        builder.setPositiveButton(R.string.OK) { _, _ -> onBackPressed() }
        builder.show()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}