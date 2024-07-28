package com.wira_fkom.pajawanedumobile.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.wira_fkom.pajawanedumobile.MainActivity
import com.wira_fkom.pajawanedumobile.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = FirebaseAuth.getInstance()

        // Periksa apakah pengguna sudah login
        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        title = "Login Activity"

        binding?.btnRegister?.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        binding?.btnLogin?.setOnClickListener {
            signInUser()
        }
    }

    private fun signInUser() {
        val email = binding?.etEmail?.text.toString()
        val password = binding?.etPassword?.text.toString()
        if (validateForm(email, password)) {
            showProgressBar()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        showToast("Oops! Something went wrong")
                    }
                    hideProgressBar()
                }
        }
    }

    private fun validateForm(email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding?.tilEmail?.error = "Enter valid email address"
                false
            }
            TextUtils.isEmpty(password) -> {
                binding?.tilPassword?.error = "Enter password"
                false
            }
            else -> true
        }
    }

    private fun showProgressBar() {
        // Implement show progress bar logic
    }

    private fun hideProgressBar() {
        // Implement hide progress bar logic
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
