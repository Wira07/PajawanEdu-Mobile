package com.wira_fkom.pajawanedumobile.learning

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable the Up button

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

        // Setel TextView agar hanya menampilkan 8 baris teks pertama
        binding.tvDescription.maxLines = 8
        binding.tvDescription.ellipsize = TextUtils.TruncateAt.END

        // Setup "See More" button
        setupSeeMoreButton()

        // Setup share buttons
    }

    private fun setupSeeMoreButton() {
        binding.btnExpand.setOnClickListener {
            if (isExpanded) {
                // Setel ke keadaan ringkas
                binding.tvDescription.maxLines = 8
                binding.tvDescription.ellipsize = TextUtils.TruncateAt.END
                binding.btnExpand.text = "Selengkapnya"
            } else {
                // Setel ke keadaan penuh
                binding.tvDescription.maxLines = Int.MAX_VALUE
                binding.tvDescription.ellipsize = null
                binding.btnExpand.text = "Tampilkan Lebih Sedikit"
            }
            isExpanded = !isExpanded

            // Memungkinkan scroll setelah teks diperluas
            binding.scrollView.post {
                binding.scrollView.fullScroll(View.FOCUS_DOWN)
            }
        }
    }

    override fun onBackPressed() {
        // Menghapus animasi transisi
        super.onBackPressed()
        // Tidak ada animasi khusus saat kembali
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
