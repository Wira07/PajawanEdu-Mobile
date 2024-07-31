package com.wira_fkom.pajawanedumobile.quiz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.wira_fkom.pajawanedumobile.data.Question
import com.wira_fkom.pajawanedumobile.databinding.ActivityQuizMathBinding

class QuizMathActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizMathBinding

    // Daftar pertanyaan
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
        // Tambahkan lebih banyak pertanyaan sesuai kebutuhan
    )

    private var currentQuestionIndex = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizMathBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadQuestion(currentQuestionIndex)

        title = "Pajawanlor Books"

        binding.firstOptionBtn.setOnClickListener { checkAnswer(0) }
        binding.secondOptionBtn.setOnClickListener { checkAnswer(1) }
        binding.thirdOptionBtn.setOnClickListener { checkAnswer(2) }
        binding.fourthOptionBtn.setOnClickListener { checkAnswer(3) }

        binding.twoOptionRemoveBtn.setOnClickListener {
            // Contoh menghapus dua opsi yang salah (implementasikan logika sesuai kebutuhan)
            Toast.makeText(this, "Dua opsi yang salah telah dihapus", Toast.LENGTH_SHORT).show()
        }
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
    }

    private fun checkAnswer(selectedIndex: Int) {
        val question = questions[currentQuestionIndex]
        if (selectedIndex == question.correctAnswerIndex) {
            correctAnswers++
            showAlert("Benar!", "Jawaban Anda benar.")
        } else {
            showAlert("Salah!", "Jawaban yang benar adalah: ${question.options[question.correctAnswerIndex]}")
        }

        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            loadQuestion(currentQuestionIndex)
        } else {
            showResults()
        }
    }

    private fun showAlert(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showResults() {
        // Tampilkan hasil kepada pengguna
        val scoreMessage = "Anda menjawab $correctAnswers dari ${questions.size} pertanyaan dengan benar!"
        AlertDialog.Builder(this)
            .setTitle("Hasil Quiz")
            .setMessage(scoreMessage)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                // Opsional: navigasikan kembali ke layar utama atau reset kuis
            }
            .show()
    }
}
