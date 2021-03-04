package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private var sysUIHidden = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        hideStatusBar()

        val username = intent.getStringExtra(Constants.USER_NAME)
        val correctAnswers = intent.getStringExtra(Constants.CORRECT_ANSWERS)
        val totalQuestions = intent.getStringExtra(Constants.TOTAL_QUESTIONS)

        tvName.text = username
        tvScore.text = "Du hast $correctAnswers von $totalQuestions richtig beantwortet"

        btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
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
}