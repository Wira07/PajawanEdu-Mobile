package com.wira_fkom.pajawanedumobile.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.widget.ProgressBar
import com.wira_fkom.pajawanedumobile.GetStarted
import com.wira_fkom.pajawanedumobile.R
import com.wira_fkom.pajawanedumobile.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH: Long = 3000 // 3000 milliseconds = 3 seconds
    private lateinit var progressBar: ProgressBar
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var topAnim: Animation
    private lateinit var bottomAnim: Animation

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

        title = "SplashScreen"

        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = ProgressBar.VISIBLE

        Handler().postDelayed({
            val mainIntent = Intent(this@SplashScreenActivity, GetStarted::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}
