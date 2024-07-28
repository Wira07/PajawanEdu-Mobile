package com.wira_fkom.pajawanedumobile.Splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.wira_fkom.pajawanedumobile.GetStarted
import com.wira_fkom.pajawanedumobile.MainActivity
import com.wira_fkom.pajawanedumobile.R
import com.wira_fkom.pajawanedumobile.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH: Long = 3000 // 3000 milliseconds = 3 seconds
    private lateinit var progressBar: ProgressBar
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var topAnim: Animation
    private lateinit var bottomAnim: Animation
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "SplashScreen"

        // Animations
        topAnim = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = android.view.animation.AnimationUtils.loadAnimation(this,
            R.anim.bottom_animation
        )

        binding.image.startAnimation(topAnim)
        binding.text.startAnimation(bottomAnim)

        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = ProgressBar.VISIBLE

        auth = FirebaseAuth.getInstance()

        Handler().postDelayed({
            val nextActivity = if (auth.currentUser != null) {
                MainActivity::class.java
            } else {
                GetStarted::class.java
            }
            val mainIntent = Intent(this@SplashScreenActivity, nextActivity)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}
