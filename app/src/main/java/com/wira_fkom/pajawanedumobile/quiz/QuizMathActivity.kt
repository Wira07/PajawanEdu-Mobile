package com.wira_fkom.pajawanedumobile.quiz

import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.wira_fkom.pajawanedumobile.databinding.ActivityQuizMathBinding

class QuizMathActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizMathBinding

    private val questions = listOf(
        "Berapakah hasil dari 7 + 8?",
        "Berapakah hasil dari 9 - 3?",
        "Berapakah hasil dari 6 x 6?",
        "Berapakah hasil dari 12 / 4?",
        "Berapakah hasil dari 5 + 9?",
        "Berapakah hasil dari 10 - 7?",
        "Berapakah hasil dari 8 x 7?",
        "Berapakah hasil dari 15 / 3?",
        "Berapakah hasil dari 4 + 6?",
        "Berapakah hasil dari 9 - 4?"
    )
    private val options = listOf(
        listOf("14", "15", "16", "17"),
        listOf("5", "6", "7", "8"),
        listOf("35", "36", "37", "38"),
        listOf("2", "3", "4", "5"),
        listOf("12", "13", "14", "15"),
        listOf("2", "3", "4", "5"),
        listOf("54", "55", "56", "57"),
        listOf("4", "5", "6", "7"),
        listOf("8", "9", "10", "11"),
        listOf("4", "5", "6", "7")
    )
    private val correctAnswers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    private var currentQuestionIndex = 0
    private var isAnswerCorrect = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizMathBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadQuestion()

        binding.submitButton.setOnClickListener {
            checkAnswer()
        }

        binding.nextButton.setOnClickListener {
            nextQuestion()
        }

        binding.previousButton.setOnClickListener {
            previousQuestion()
        }
    }

    private fun loadQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        val currentOptions = options[currentQuestionIndex]

        binding.questionText.text = currentQuestion
        binding.answer1.text = currentOptions[0]
        binding.answer2.text = currentOptions[1]
        binding.answer3.text = currentOptions[2]
        binding.answer4.text = currentOptions[3]

        binding.answerGroup.clearCheck()
        binding.resultText.text = ""
        binding.nextButton.isEnabled = false
        isAnswerCorrect = false
    }

    private fun checkAnswer() {
        val selectedId = binding.answerGroup.checkedRadioButtonId
        if (selectedId != -1) {
            val selectedAnswer = findViewById<RadioButton>(selectedId)
            val selectedIndex = binding.answerGroup.indexOfChild(selectedAnswer)
            if (selectedIndex == correctAnswers[currentQuestionIndex]) {
                binding.resultText.text = "Benar!"
                binding.nextButton.isEnabled = true
                isAnswerCorrect = true
            } else {
                binding.resultText.text = "Salah. Jawaban yang benar adalah ${options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]}."
            }
        } else {
            binding.resultText.text = "Silakan pilih jawaban."
        }
    }

    private fun nextQuestion() {
        if (isAnswerCorrect) {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                loadQuestion()
            }
        } else {
            binding.resultText.text = "Jawaban belum benar, coba lagi!"
        }
    }

    private fun previousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--
            loadQuestion()
        }
    }
}

