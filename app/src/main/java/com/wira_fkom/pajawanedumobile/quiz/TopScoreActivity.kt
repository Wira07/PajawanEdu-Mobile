package com.wira_fkom.pajawanedumobile.quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wira_fkom.pajawanedumobile.MainActivity
import com.wira_fkom.pajawanedumobile.databinding.ActivityTopScoreBinding

class TopScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 10)

        val scoreMessage = "Anda menjawab $correctAnswers dari $totalQuestions pertanyaan dengan benar!"
        binding.scoreMessage.text = scoreMessage

        binding.collectPrizeButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
