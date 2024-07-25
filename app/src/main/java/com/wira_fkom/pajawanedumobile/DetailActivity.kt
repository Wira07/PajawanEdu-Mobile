package com.wira_fkom.pajawanedumobile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wira_fkom.pajawanedumobile.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val PICK_VIDEO_REQUEST = 1
    private var videoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the back button to finish the activity
        binding.backButton.setOnClickListener {
            finish()
        }

        // Set up the favorite button functionality
        binding.favoriteButton.setOnClickListener {
            // Add your favorite functionality here
        }

        // Set the content
        binding.titleText.text = "Design Thinking"
        binding.descriptionText.text = "Lorem ipsum dolor sit amet consectetur. Consectetur ut pulvinar semper massa dictum ornare. Odio mi pellentesque aliquet turpis integer. A a gravida duis libero. Sollicitudin massa maecenas viverra condimentum pellentesque. Aliquam interdum volutpat vitae ac velit gravida erat feugiat at. In sit nec magna neque congue bibendum ullamcorper sed odio. Sagittis consequat aenean dui justo sed vel."

        // Set up the VideoView
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)

        // Upload button click listener
        binding.uploadButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "video/*"
            startActivityForResult(intent, PICK_VIDEO_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_VIDEO_REQUEST && resultCode == Activity.RESULT_OK) {
            videoUri = data?.data
            if (videoUri != null) {
                binding.videoView.setVideoURI(videoUri)
                binding.videoView.start()
            } else {
                Toast.makeText(this, "Failed to load video", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
