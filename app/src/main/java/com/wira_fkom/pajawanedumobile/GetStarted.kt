package com.wira_fkom.pajawanedumobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.wira_fkom.pajawanedumobile.databinding.ActivityGetStartedBinding
import com.wira_fkom.pajawanedumobile.login.LoginActivity


class GetStarted : AppCompatActivity() {
    private lateinit var binding: ActivityGetStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Get Started"

        // Animasi fade in dan zoom in untuk gambar
        val fadeInZoomIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_zoom_in)
        binding.Gambar.startAnimation(fadeInZoomIn)

        // Animasi fade in dan slide up untuk teks deskripsi
        val fadeInSlideUp = AnimationUtils.loadAnimation(this, R.anim.fade_in_slide_up)
        binding.text.startAnimation(fadeInSlideUp)

        // Animasi scale up dan fade in untuk tombol
        val scaleUpFadeIn = AnimationUtils.loadAnimation(this, R.anim.scale_up_fade_in)
        binding.button.startAnimation(scaleUpFadeIn)

        binding.button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}