package com.wira_fkom.pajawanedumobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wira_fkom.pajawanedumobile.adapter.ExpertiseAdapter
import com.wira_fkom.pajawanedumobile.databinding.ActivityQuizBinding
import com.wira_fkom.pajawanedumobile.quiz.LevelMathActivity
import com.wira_fkom.pajawanedumobile.quiz.QuizIPAActivity
import com.wira_fkom.pajawanedumobile.quiz.QuizIPSActivity
import com.wira_fkom.pajawanedumobile.quiz.QuizMathActivity

class QuizActivity : AppCompatActivity(), ExpertiseAdapter.OnItemClickListener {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var expertiseAdapter: ExpertiseAdapter

    private val expertises = listOf(
        "Matematika",
        "IPA",
        "IPS"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        expertiseAdapter = ExpertiseAdapter(expertises, this)

        title = "Pajawanlor Books"

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@QuizActivity)
            adapter = expertiseAdapter
        }

        binding.nextButton.setOnClickListener {
            // Handle button click, e.g., navigate to next screen or show selected item
        }
    }

    override fun onItemClick(expertise: String) {
        val intent = when (expertise) {
            "Matematika" -> Intent(this, QuizMathActivity::class.java)
            "IPA" -> Intent(this, QuizIPAActivity::class.java)
            "IPS" -> Intent(this, QuizIPSActivity::class.java)
            else -> null
        }
        intent?.let { startActivity(it) }
    }
}
