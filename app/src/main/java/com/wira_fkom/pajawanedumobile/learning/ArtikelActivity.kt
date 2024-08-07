package com.wira_fkom.pajawanedumobile.learning

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wira_fkom.pajawanedumobile.R
import com.wira_fkom.pajawanedumobile.adapter.ArticleAdapter
import com.wira_fkom.pajawanedumobile.data.Article
import com.wira_fkom.pajawanedumobile.databinding.ActivityArtikelBinding

class ArtikelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Artikel"

        val articles = listOf(
            Article("Apa Itu Komputer?", "Belajar tentang apa itu komputer dan bagaimana mereka membantu kita setiap hari.", "https://i.pinimg.com/736x/0f/f1/67/0ff167f2ffba09f6337591aa6b0b694d.jpg"),
            Article("Internet untuk Anak-anak", "Pelajari tentang internet dan cara menggunakannya dengan aman.", "https://cdn.pixabay.com/photo/2015/06/24/15/45/keyboard-820274_960_720.jpg"),
            Article("Robot yang Membantu", "Bagaimana robot dapat membantu pekerjaan sehari-hari.", "https://i.pinimg.com/564x/08/6c/32/086c32533bc3dedbda109b96136f2e22.jpg"),
            Article("Telepon Pintar", "Mengapa telepon pintar sangat berguna dan bagaimana mereka bekerja.", "https://i.pinimg.com/564x/ea/7e/18/ea7e18a2d223c07800a6a3a3b9268589.jpg"),
            Article("Menjaga Kesehatan", "Tips sederhana untuk menjaga kesehatan tubuh.", "https://i.pinimg.com/564x/c5/59/af/c559af27267f60b0583a10675261ca9d.jpg"),
            Article("Makanan Sehat", "Jenis-jenis makanan yang baik untuk tubuh kita.", "https://i.pinimg.com/736x/1c/bf/3e/1cbf3e3586d13e5f3e33a74540eddb5b.jpg"),
            Article("Sejarah Televisi", "Bagaimana televisi ditemukan dan berkembang.", "https://asset.kompas.com/crops/qWqbJ53KDnJ4MUrpkLMLNKTofew=/0x82:640x509/750x500/data/photo/2022/11/03/63635e408a785.jpeg"),
            Article("Penemuan Lampu", "Cerita tentang penemuan lampu dan bagaimana itu mengubah dunia.", "https://asset-a.grid.id//crop/112x0:561x504/700x0/photo/2019/12/03/4255448458.jpg"),
            Article("Kesehatan Gigi", "Cara merawat gigi agar tetap sehat dan kuat.", "https://rsjakarta.co.id/wp-content/uploads/2022/03/images.png"),
            Article("Sejarah Sepeda", "Bagaimana sepeda ditemukan dan digunakan sampai sekarang.", "https://awsimages.detik.net.id/community/media/visual/2020/07/02/foto-sejarah-sepeda-3_169.jpeg?w=600&q=90")
        )

        val adapter = ArticleAdapter(articles) { article ->
            val intent = Intent(this, DetailArtikel::class.java).apply {
                putExtra("title", article.title)
                putExtra("description", article.description)
                putExtra("imageUrl", article.imageUrl)
            }
            startActivity(intent)
        }

        binding.itemKu.layoutManager = LinearLayoutManager(this)
        binding.itemKu.adapter = adapter
    }
}
