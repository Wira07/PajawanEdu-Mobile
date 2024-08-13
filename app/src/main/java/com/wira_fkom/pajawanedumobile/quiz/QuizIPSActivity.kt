package com.wira_fkom.pajawanedumobile.quiz

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.wira_fkom.pajawanedumobile.data.Question
import com.wira_fkom.pajawanedumobile.databinding.ActivityQuizIpsactivityBinding

class QuizIPSActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizIpsactivityBinding
    private var countDownTimer: CountDownTimer? = null
    private val countdownTime: Long = 60000 // 60 seconds
    private val interval: Long = 1000 // 1 second
    private var timeLeft: Long = countdownTime

    private val questions = listOf(
        Question("Presiden pertama?", listOf("Soekarno", "Hatta", "Suharto", "Habibie"), 0),
        Question("Ibu kota?", listOf("Jakarta", "Bandung", "Surabaya", "Medan"), 0),
        Question("Proklamasi?", listOf("17 Aug", "20 Mei", "1 Jun", "10 Nov"), 0),
        Question("Penulis 'Laskar Pelangi'?", listOf("Andrea", "Pramoedya", "Ahmad", "Dee"), 0),
        Question("Mata uang?", listOf("Rupiah", "Ringgit", "Baht", "Peso"), 0),
        Question("Borobudur?", listOf("Jatim", "Jabar", "Jateng", "Bali"), 2),
        Question("Gunung tertinggi?", listOf("Semeru", "Rinjani", "Kerinci", "Jaya"), 3),
        Question("Penemu lambang?", listOf("Hamid", "Hatta", "Sud", "Yani"), 0),
        Question("Tari Bali?", listOf("Kecak", "Saman", "Piring", "Jaipong"), 0),
        Question("Kelahiran Pancasila?", listOf("Jakarta", "Bandung", "Jogja", "Surabaya"), 2)
    )

    private var currentQuestionIndex = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizIpsactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "EduKids"

        // Initialize question loading
        loadQuestion(currentQuestionIndex)

        binding.firstOptionBtn.setOnClickListener { checkAnswer(0) }
        binding.secondOptionBtn.setOnClickListener { checkAnswer(1) }
        binding.thirdOptionBtn.setOnClickListener { checkAnswer(2) }
        binding.fourthOptionBtn.setOnClickListener { checkAnswer(3) }

        binding.removeOptionsBtn.setOnClickListener {
            // Example of removing two incorrect options (implement logic as needed)
            Toast.makeText(this, "Dua opsi yang salah telah dihapus", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startCountdown() {
        // Cancel any existing timer
        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(timeLeft, interval) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                val secondsRemaining = millisUntilFinished / 1000
                binding.timerTextView.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                if (!isFinishing) {
                    showTimeUpDialog()
                }
            }
        }.start()
    }

    private fun showTimeUpDialog() {
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
        binding.questionTextView.text = question.questionText
        binding.firstOptionBtn.text = question.options[0]
        binding.secondOptionBtn.text = question.options[1]
        binding.thirdOptionBtn.text = question.options[2]
        binding.fourthOptionBtn.text = question.options[3]
        binding.questionNumberTextView.text = "Pertanyaan ${index + 1}/${questions.size}"

        // Reset timer for new question
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

    override fun onDestroy() {
        super.onDestroy()
        // Cancel the timer when the activity is destroyed
        countDownTimer?.cancel()
    }
}
