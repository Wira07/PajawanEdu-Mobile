package com.wira_fkom.pajawanedumobile.modul

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.wira_fkom.pajawanedumobile.R
import com.wira_fkom.pajawanedumobile.databinding.ActivityIpaactivityBinding

class IPAActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIpaactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIpaactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Ilmu Pengetahuan Alam"

        // Set values using binding
        binding.courseTitle.text = "Belajar IPA"
        binding.creatorName.text = "Dibuat oleh Guru IPA"
        binding.courseDescription.text = "15 Pelajaran • 12 Jam"
        binding.aboutCourse.text = "Modul ini membantu siswa SD untuk belajar IPA dengan cara yang menyenangkan dan interaktif."

        // Mengatur nilai statis untuk pelajaran
        binding.lesson1Title.text = "1. Pengenalan Alam"
        binding.lesson1Duration.text = "8 menit"

        binding.lesson2Title.text = "2. Sistem Tata Surya"
        binding.lesson2Duration.text = "10 menit"

        binding.lesson3Title.text = "3. Ekosistem"
        binding.lesson3Duration.text = "12 menit"

        // Set click listeners on play buttons
        binding.lesson1.findViewById<ImageView>(R.id.playButton1).setOnClickListener {
            Toast.makeText(this, "Playing lesson 1", Toast.LENGTH_SHORT).show()
            // Add your play logic here
        }

        binding.lesson2.findViewById<ImageView>(R.id.playButton2).setOnClickListener {
            Toast.makeText(this, "Playing lesson 2", Toast.LENGTH_SHORT).show()
            // Add your play logic here
        }

        binding.lesson3.findViewById<ImageView>(R.id.playButton3).setOnClickListener {
            Toast.makeText(this, "Playing lesson 3", Toast.LENGTH_SHORT).show()
            // Add your play logic here
        }

        // Set up the YouTube player view
        lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo("gLy3HIx2DfE", 0f)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
    }
}
