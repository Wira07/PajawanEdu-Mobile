package com.wira_fkom.pajawanedumobile.quiz

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.wira_fkom.pajawanedumobile.databinding.ActivityQuizBahasaactivityBinding

class QuizBahasaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBahasaactivityBinding

    private val questions = listOf(
        Question("Hewan tercepat?", listOf("Cheetah", "Kelinci", "Singa", "Gajah"), 0),
        Question("Planet terdekat ke Matahari?", listOf("Merkurius", "Venus", "Bumi", "Mars"), 0),
        Question("Buah berwarna merah?", listOf("Apel", "Pisang", "Anggur", "Melon"), 0),
        Question("Ibukota Jepang?", listOf("Tokyo", "Osaka", "Kyoto", "Nagasaki"), 0),
        Question("Bendera merah putih?", listOf("Indonesia", "Malaysia", "Singapura", "Thailand"), 0),
        Question("Gunung tertinggi?", listOf("Everest", "Fuji", "Kilimanjaro", "Alpen"), 0),
        Question("Laut terluas?", listOf("Pasifik", "Atlantik", "Hindia", "Arktik"), 0),
        Question("Bulan terbesar?", listOf("Ganymede", "Titan", "Europa", "Callisto"), 0),
        Question("Hewan mamalia?", listOf("Paus", "Ikan", "Katak", "Kura-kura"), 0),
        Question("Bahan utama roti?", listOf("Gandum", "Beras", "Jagung", "Kacang"), 0)
    )

    private var currentQuestionIndex = 0
    private var score = 0

    private lateinit var countDownTimer: CountDownTimer
    private val timeLimit = 60000L // 60 seconds
    private var timeLeft = timeLimit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBahasaactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadQuestion()

        title = "EduKids"

        binding.firstOptionBtn.setOnClickListener { checkAnswer(0) }
        binding.secondOptionBtn.setOnClickListener { checkAnswer(1) }
        binding.thirdOptionBtn.setOnClickListener { checkAnswer(2) }
        binding.fourthOptionBtn.setOnClickListener { checkAnswer(3) }

        binding.twoOptionRemoveBtn.setOnClickListener { removeTwoOptions() }

        startTimer()
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]
            binding.questionTxt.text = currentQuestion.text
            binding.firstOptionBtn.text = currentQuestion.options[0]
            binding.secondOptionBtn.text = currentQuestion.options[1]
            binding.thirdOptionBtn.text = currentQuestion.options[2]
            binding.fourthOptionBtn.text = currentQuestion.options[3]
            binding.noOfQuestionTxt.text = "Pertanyaan ${currentQuestionIndex + 1}/${questions.size}"
            timeLeft = timeLimit
            startTimer()
        } else {
            finishQuiz()
        }
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedOptionIndex == currentQuestion.correctOptionIndex) {
            score++
            showAlert("Jawaban Benar", "Anda benar!", ::nextQuestion)
        } else {
            showAlert("Jawaban Salah", "Jawaban yang benar adalah: ${currentQuestion.options[currentQuestion.correctOptionIndex]}", ::nextQuestion)
        }
    }

    private fun showAlert(title: String, message: String, onDismiss: () -> Unit) {
        countDownTimer.cancel() // Pause the timer
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                onDismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun nextQuestion() {
        currentQuestionIndex++
        loadQuestion()
    }

    private fun removeTwoOptions() {
        // Implementasi logika untuk menghapus dua pilihan yang salah
        // Misalnya dengan menghilangkan dua opsi yang salah secara acak
    }

    private fun startTimer() {
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
        countDownTimer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                binding.timeTxt.text = (millisUntilFinished / 1000).toString()
                binding.circularProgressBar.progress = (timeLeft * 100 / timeLimit).toInt()
            }

            override fun onFinish() {
                nextQuestion()
            }
        }.start()
    }

    private fun finishQuiz() {
        // Tampilkan hasil akhir atau pindahkan ke activity lain
        // Misalnya menampilkan skor dan mengakhiri quiz
        binding.questionTxt.text = "Kuis Selesai! Skor Anda: $score"
        binding.firstOptionBtn.isEnabled = false
        binding.secondOptionBtn.isEnabled = false
        binding.thirdOptionBtn.isEnabled = false
        binding.fourthOptionBtn.isEnabled = false
        binding.twoOptionRemoveBtn.isEnabled = false
    }
}

data class Question(
    val text: String,
    val options: List<String>,
    val correctOptionIndex: Int
)
