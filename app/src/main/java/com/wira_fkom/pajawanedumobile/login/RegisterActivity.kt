package com.wira_fkom.pajawanedumobile.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.wira_fkom.pajawanedumobile.MainActivity
import com.wira_fkom.pajawanedumobile.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Register Activity"

        auth = FirebaseAuth.getInstance()

        binding.baru.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.SignIn2.setOnClickListener { registerUser() }
    }

    private fun registerUser() {
        val name = binding.etSinUpName.text.toString()
        val email = binding.etSinUpEmail.text.toString()
        val password = binding.etSinUpPassword.text.toString()

        if (validateForm(name, email, password)) {
            showProgressBar()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    hideProgressBar()
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        user?.let {
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build()
                            it.updateProfile(profileUpdates)
                                .addOnCompleteListener { profileUpdateTask ->
                                    if (profileUpdateTask.isSuccessful) {
                                        showToast("User successfully created")
                                        saveUsernameToPreferences(name)
                                        navigateToMain(it)
                                    } else {
                                        showToast("Failed to update profile")
                                    }
                                }
                        }
                    } else {
                        showToast("Oops! Something went wrong")
                    }
                }
        }
    }

    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                binding.tilName.error = "Enter name"
                false
            }
            TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.tilEmail.error = "Enter a valid email address"
                false
            }
            TextUtils.isEmpty(password) -> {
                binding.tilPassword.error = "Enter password"
                false
            }
            else -> true
        }
    }

    private fun navigateToMain(user: FirebaseUser) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("username", user.displayName)
        startActivity(intent)
        finish()
    }

    private fun saveUsernameToPreferences(username: String?) {
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.apply()
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
