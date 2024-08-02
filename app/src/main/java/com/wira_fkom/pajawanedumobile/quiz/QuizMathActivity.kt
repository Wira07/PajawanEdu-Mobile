package com.wira_fkom.pajawanedumobile.quiz

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.wira_fkom.pajawanedumobile.data.Question
import com.wira_fkom.pajawanedumobile.databinding.ActivityQuizMathBinding

class QuizMathActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizMathBinding
    private var countDownTimer: CountDownTimer? = null
    private val countdownTime: Long = 60000 // 60 seconds
    private val interval: Long = 1000 // 1 second
    private var timeLeft: Long = countdownTime

    // List of questions
    private val questions = listOf(
        Question("25 + ? = 50", listOf("25", "15", "5", "10"), 0),
        Question("10 + 5 = ?", listOf("10", "15", "20", "25"), 1),
        Question("7 - 3 = ?", listOf("4", "5", "3", "2"), 0),
        Question("5 * 3 = ?", listOf("15", "10", "20", "8"), 0),
        Question("9 / 3 = ?", listOf("1", "2", "3", "4"), 2),
        Question("12 - 8 = ?", listOf("2", "4", "6", "8"), 1),
        Question("6 + 7 = ?", listOf("11", "12", "13", "14"), 2),
        Question("14 / 2 = ?", listOf("5", "6", "7", "8"), 2),
        Question("8 * 2 = ?", listOf("14", "16", "18", "20"), 1),
        Question("18 - 5 = ?", listOf("12", "13", "14", "15"), 1)
        // Add more questions as needed
    )

    private var currentQuestionIndex = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizMathBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadQuestion(currentQuestionIndex)

        binding.firstOptionBtn.setOnClickListener { checkAnswer(0) }
        binding.secondOptionBtn.setOnClickListener { checkAnswer(1) }
        binding.thirdOptionBtn.setOnClickListener { checkAnswer(2) }
        binding.fourthOptionBtn.setOnClickListener { checkAnswer(3) }

        binding.twoOptionRemoveBtn.setOnClickListener {
            // Example of removing two incorrect options (implement logic as needed)
            Toast.makeText(this, "Dua opsi yang salah telah dihapus", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startCountdown() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(timeLeft, interval) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                val secondsRemaining = millisUntilFinished / 1000
                binding.timeTxt.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                showTimeUpDialog()
            }
        }.start()
    }

    private fun showTimeUpDialog() {
        countDownTimer?.cancel()
        AlertDialog.Builder(this)
            .setTitle("Waktu Habis!")
            .setMessage("Waktu Anda telah habis untuk menjawab pertanyaan ini.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                nextQuestion()
            }
            .show()
    }

    private fun loadQuestion(index: Int) {
        if (index >= questions.size) {
            showResults()
            return
        }

        val question = questions[index]
        binding.questionTxt.text = question.questionText
        binding.firstOptionBtn.text = question.options[0]
        binding.secondOptionBtn.text = question.options[1]
        binding.thirdOptionBtn.text = question.options[2]
        binding.fourthOptionBtn.text = question.options[3]
        binding.noOfQuestionTxt.text = "Pertanyaan ${index + 1}/${questions.size}"

        timeLeft = countdownTime
        startCountdown()
    }

    private fun checkAnswer(selectedIndex: Int) {
        countDownTimer?.cancel()
        val question = questions[currentQuestionIndex]
        if (selectedIndex == question.correctAnswerIndex) {
            correctAnswers++
            showCorrectAnswerDialog()
        } else {
            showIncorrectAnswerDialog(question.options[question.correctAnswerIndex])
        }
    }

    private fun showCorrectAnswerDialog() {
        AlertDialog.Builder(this)
            .setTitle("Benar!")
            .setMessage("Jawaban Anda benar!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                nextQuestion()
            }
            .show()
    }

    private fun showIncorrectAnswerDialog(correctAnswer: String) {
        AlertDialog.Builder(this)
            .setTitle("Salah!")
            .setMessage("Jawaban yang benar adalah: $correctAnswer")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                nextQuestion()
            }
            .show()
    }

    private fun nextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            loadQuestion(currentQuestionIndex)
        } else {
            showResults()
        }
    }

    private fun showResults() {
        val intent = Intent(this, TopScoreActivity::class.java)
        intent.putExtra("CORRECT_ANSWERS", correctAnswers)
        intent.putExtra("TOTAL_QUESTIONS", questions.size)
        startActivity(intent)
        finish() // Optional: finish current activity
    }
}
