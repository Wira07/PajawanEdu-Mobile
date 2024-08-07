package com.wira_fkom.pajawanedumobile.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
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

        // Disable the sign in button initially
        binding.SignIn2.isEnabled = false

        // Add text change listeners to enable the sign in button only when form is valid
        binding.etSinUpName.addTextChangedListener(textWatcher)
        binding.etSinUpEmail.addTextChangedListener(textWatcher)
        binding.etSinUpPassword.addTextChangedListener(textWatcher)

        binding.SignIn2.setOnClickListener { registerUser() }
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            enableSignInButtonIfFormValid()
        }
        override fun afterTextChanged(s: Editable?) {}
    }

    private fun enableSignInButtonIfFormValid() {
        val name = binding.etSinUpName.text.toString()
        val email = binding.etSinUpEmail.text.toString()
        val password = binding.etSinUpPassword.text.toString()
        binding.SignIn2.isEnabled = validateForm(name, email, password)
    }

    private fun registerUser() {
        val name = binding.etSinUpName.text.toString()
        val email = binding.etSinUpEmail.text.toString()
        val password = binding.etSinUpPassword.text.toString()

        if (!validateForm(name, email, password)) {
            showToast("Semua data harus diisi dengan benar.")
            return
        }

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
                                    showToast("User berhasil dibuat")
                                    saveUsernameToPreferences(name)
                                    navigateToMain(it)
                                } else {
                                    showToast("Gagal memperbarui profil")
                                }
                            }
                    }
                } else {
                    showToast("Oops! Terjadi kesalahan")
                }
            }
    }

    private fun validateForm(name: String, email: String, password: String): Boolean {
        var valid = true

        if (TextUtils.isEmpty(name)) {
            binding.tilName.error = "Masukkan nama"
            valid = false
        } else {
            binding.tilName.error = null
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Masukkan alamat email yang valid"
            valid = false
        } else {
            binding.tilEmail.error = null
        }

        if (TextUtils.isEmpty(password)) {
            binding.tilPassword.error = "Masukkan kata sandi"
            valid = false
        } else {
            binding.tilPassword.error = null
        }

        return valid
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
