package com.example.quizapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var sysUIHidden = false;
    private var mUserName: String? = null
    private var mCurrentPosition = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        hideStatusBar()

        //Liest was über intent weitergegeben wird
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        setQuestion()

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)


    }

    private fun setQuestion() {

        defaultOptionsView()

        mQuestionList = Constants.getQuestions()
        Log.i("Fragen", "${mQuestionList!!.size}")

        val question: Question? = mQuestionList!![mCurrentPosition-1]

        progressBar.progress = mCurrentPosition
        tvProgress.text = "$mCurrentPosition/" + progressBar.max

        tvQuestion.text = question!!.question
        ivFlag.setImageResource(question.image)

        tvOptionOne.text = question.option1
        tvOptionTwo.text = question.option2
        tvOptionThree.text = question.option3
        tvOptionFour.text = question.option4
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tvOptionOne)
        options.add(1, tvOptionTwo)
        options.add(2, tvOptionThree)
        options.add(3, tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7a8089"))
            option.typeface = Typeface.DEFAULT

            //THIS is how you choose a ressource!!
            option.background = ContextCompat.getDrawable(
                    this, R.drawable.default_option_border
            )
        }
    }

    override fun onResume() {
        super.onResume()
        hideStatusBar()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideStatusBar()
    }

    //perfect way to hide status bar, see the theme file as well for additional stuff
    private fun hideStatusBar() {
        sysUIHidden = true
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        // Hide the nav bar and status bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Hide nav bar
                        or View.SYSTEM_UI_FLAG_FULLSCREEN // Hide status bar
                )

    }

    override fun onClick(v: View?) {

        when(v?.id) {
            tvOptionOne.id -> {
                selectedOptionView(tvOptionOne, 1)
            }
            tvOptionTwo.id -> {
                selectedOptionView(tvOptionTwo, 2)
            }
            tvOptionThree.id -> {
                selectedOptionView(tvOptionThree, 3)
            }
            tvOptionFour.id -> {
                selectedOptionView(tvOptionFour, 4)
            }
        }

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOption = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363a43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)

        //THIS is how you choose a ressource!!
        tv.background = ContextCompat.getDrawable(
                this, R.drawable.selected_option_border
        )
    }
}