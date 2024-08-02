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

    // List of questions
    private val questions = listOf(
        Question("Siapa Presiden pertama Indonesia?", listOf("Soekarno", "Suharto", "Habibie", "Megawati"), 0),
        Question("Apa ibu kota negara Indonesia?", listOf("Surabaya", "Bandung", "Jakarta", "Medan"), 2),
        Question("Kapan Proklamasi Kemerdekaan Indonesia?", listOf("17 Agustus 1945", "20 Mei 1945", "1 Juni 1945", "10 November 1945"), 0),
        Question("Siapa penulis novel 'Laskar Pelangi'?", listOf("Andrea Hirata", "Pramoedya Ananta Toer", "Ahmad Tohari", "Dee Lestari"), 0),
        Question("Apa mata uang negara Indonesia?", listOf("Rupiah", "Ringgit", "Baht", "Peso"), 0),
        Question("Dimana Candi Borobudur berada?", listOf("Jawa Timur", "Jawa Barat", "Jawa Tengah", "Bali"), 2),
        Question("Apa gunung tertinggi di Indonesia?", listOf("Gunung Semeru", "Gunung Rinjani", "Gunung Kerinci", "Puncak Jaya"), 3),
        Question("Siapa penemu lambang negara Indonesia?", listOf("Sultan Hamid II", "Mohammad Hatta", "Soedirman", "Ahmad Yani"), 0),
        Question("Apa nama tari tradisional dari Bali?", listOf("Tari Kecak", "Tari Saman", "Tari Piring", "Tari Jaipong"), 0),
        Question("Dimana tempat kelahiran Pancasila?", listOf("Jakarta", "Bandung", "Yogyakarta", "Surabaya"), 2)
        // Add more questions as needed
    )

    private var currentQuestionIndex = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizIpsactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(timeLeft, interval) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                val secondsRemaining = millisUntilFinished / 1000
                binding.timerTextView.text = secondsRemaining.toString()
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
        binding.questionTextView.text = question.questionText
        binding.firstOptionBtn.text = question.options[0]
        binding.secondOptionBtn.text = question.options[1]
        binding.thirdOptionBtn.text = question.options[2]
        binding.fourthOptionBtn.text = question.options[3]
        binding.questionNumberTextView.text = "Pertanyaan ${index + 1}/${questions.size}"

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
