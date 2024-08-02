package com.wira_fkom.pajawanedumobile.quiz

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.wira_fkom.pajawanedumobile.databinding.ActivityQuizBahasaactivityBinding

class QuizIBahasaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBahasaactivityBinding

    private val questions = listOf(
        Question("Apa ibu kota Indonesia?", listOf("Jakarta", "Bandung", "Surabaya", "Medan"), 0),
        Question("Di mana candi Borobudur berada?", listOf("Yogyakarta", "Magelang", "Solo", "Semarang"), 1),
        Question("Siapa presiden pertama Indonesia?", listOf("Sukarno", "Suharto", "Jokowi", "Habibie"), 0),
        Question("Apa nama ibu kota Sumatera Utara?", listOf("Medan", "Padang", "Pekanbaru", "Banda Aceh"), 0),
        Question("Di pulau mana Bali berada?", listOf("Sumatera", "Kalimantan", "Jawa", "Bali"), 3),
        Question("Apa makanan khas Padang?", listOf("Rendang", "Sate", "Gudeg", "Pempek"), 0),
        Question("Siapa penulis novel 'Laskar Pelangi'?", listOf("Andrea Hirata", "Tere Liye", "Ahmad Fuadi", "Dee Lestari"), 0),
        Question("Apa bahasa resmi Indonesia?", listOf("Bahasa Jawa", "Bahasa Sunda", "Bahasa Indonesia", "Bahasa Bali"), 2),
        Question("Di mana letak Taman Mini Indonesia Indah?", listOf("Jakarta", "Surabaya", "Bandung", "Medan"), 0),
        Question("Apa mata uang Indonesia?", listOf("Rupiah", "Ringgit", "Dollar", "Yen"), 0)
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
        }
        currentQuestionIndex++
        loadQuestion()
    }

    private fun removeTwoOptions() {
        // Implementasi logika untuk menghapus dua pilihan yang salah
        // Misalnya dengan menghilangkan dua opsi yang salah secara acak
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                binding.timeTxt.text = (millisUntilFinished / 1000).toString()
                binding.circularProgressBar.progress = (timeLeft * 100 / timeLimit).toInt()
            }

            override fun onFinish() {
                currentQuestionIndex++
                loadQuestion()
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
