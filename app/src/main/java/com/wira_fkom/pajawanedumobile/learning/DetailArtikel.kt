package com.wira_fkom.pajawanedumobile.learning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.wira_fkom.pajawanedumobile.R
import com.wira_fkom.pajawanedumobile.databinding.ActivityDetailArtikelBinding

class DetailArtikel : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = ActivityDetailArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Deskrpisi Aplikasi"

        // Mendapatkan data dari Intent
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("imageUrl")

        // Mengisi view dengan data yang didapatkan
        binding.tvTitle.text = title
        binding.tvDescription.text = description

        overridePendingTransition(R.anim.fade_in_slide_up, R.anim.fade_in)

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error)
            .into(binding.imgPhoto)
    }
}
