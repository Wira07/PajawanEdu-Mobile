package com.wira_fkom.pajawanedumobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wira_fkom.pajawanedumobile.adapter.ExpertiseAdapter
import com.wira_fkom.pajawanedumobile.databinding.ActivityQuizBinding
import com.wira_fkom.pajawanedumobile.quiz.QuizIBahasaActivity
import com.wira_fkom.pajawanedumobile.quiz.QuizIPSActivity
import com.wira_fkom.pajawanedumobile.quiz.QuizMathActivity

class QuizActivity : AppCompatActivity(), ExpertiseAdapter.OnItemClickListener {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var expertiseAdapter: ExpertiseAdapter

    private val expertises = listOf(
        "Matematika",
        "Bahasa Indonesia",
        "IPAS"
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

    }

    override fun onItemClick(expertise: String) {
        val intent = when (expertise) {
            "Matematika" -> Intent(this, QuizMathActivity::class.java)
            "Bahasa Indonesia" -> Intent(this, QuizIBahasaActivity::class.java)
            "IPAS" -> Intent(this, QuizIPSActivity::class.java)
            else -> null
        }
        intent?.let { startActivity(it) }
    }
}
