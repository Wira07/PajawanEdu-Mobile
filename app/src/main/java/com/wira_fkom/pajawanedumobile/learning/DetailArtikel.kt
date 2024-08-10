package com.wira_fkom.pajawanedumobile.learning

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wira_fkom.pajawanedumobile.R
import com.wira_fkom.pajawanedumobile.databinding.ActivityDetailArtikelBinding

class DetailArtikel : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArtikelBinding
    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = ActivityDetailArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Deskripsi Aplikasi"

        // Mendapatkan data dari Intent
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("imageUrl")

        // Mengisi view dengan data yang didapatkan
        binding.textViewDescription.text = title
        binding.tvDescription.text = description

        // Glide untuk memuat gambar
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error)
            .into(binding.imageView)

        // Setup "See More" button
        setupSeeMoreButton()
    }

    private fun setupSeeMoreButton() {
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val btnExpand = findViewById<Button>(R.id.btnExpand)

        btnExpand.setOnClickListener {
            if (isExpanded) {
                // Setel ke keadaan ringkas
                tvDescription.maxLines = 3
                tvDescription.ellipsize = android.text.TextUtils.TruncateAt.END
                btnExpand.text = "Selengkapnya"
            } else {
                // Setel ke keadaan penuh
                tvDescription.maxLines = Int.MAX_VALUE
                tvDescription.ellipsize = null
                btnExpand.text = "Selengkapnya"
            }
            isExpanded = !isExpanded
        }
    }

    override fun onBackPressed() {
        // Menghapus animasi transisi
        super.onBackPressed()
        // Tidak ada animasi khusus saat kembali
    }
}
