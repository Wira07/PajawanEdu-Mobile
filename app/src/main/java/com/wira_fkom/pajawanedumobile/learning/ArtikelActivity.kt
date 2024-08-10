package com.wira_fkom.pajawanedumobile.learning

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
            Article("Apa Itu Komputer?",
                "Komputer adalah alat yang sangat membantu kita dalam berbagai aktivitas sehari-hari. Di sini, kita akan belajar tentang apa itu komputer, bagaimana komputer bekerja, dan bagaimana komputer membantu kita dalam menyelesaikan berbagai tugas seperti belajar, bermain, dan bekerja.",
                "https://i.pinimg.com/736x/0f/f1/67/0ff167f2ffba09f6337591aa6b0b694d.jpg"),

            Article("Internet untuk Anak-anak",
                "Internet adalah jaringan besar yang memungkinkan kita untuk terhubung dan berbagi informasi. Pelajari cara menggunakan internet dengan aman, bagaimana mencari informasi yang bermanfaat, dan cara melindungi diri dari bahaya yang mungkin ada di dunia maya.",
                "https://cdn.pixabay.com/photo/2015/06/24/15/45/keyboard-820274_960_720.jpg"),

            Article("Robot yang Membantu",
                "Robot adalah mesin cerdas yang dapat melakukan berbagai tugas. Temukan bagaimana robot digunakan dalam kehidupan sehari-hari untuk membantu pekerjaan rumah, seperti membersihkan, memasak, dan bahkan di bidang medis untuk membantu dokter.",
                "https://i.pinimg.com/564x/08/6c/32/086c32533bc3dedbda109b96136f2e22.jpg"),

            Article("Telepon Pintar",
                "Telepon pintar atau smartphone adalah alat yang sangat berguna. Pelajari berbagai fitur yang ada di telepon pintar, bagaimana telepon pintar membantu kita berkomunikasi, bermain game, dan mengakses informasi di internet.",
                "https://i.pinimg.com/564x/ea/7e/18/ea7e18a2d223c07800a6a3a3b9268589.jpg"),

            Article("Menjaga Kesehatan",
                "Menjaga kesehatan tubuh adalah hal yang sangat penting. Temukan berbagai tips dan cara sederhana yang dapat dilakukan untuk menjaga kesehatan tubuh kita, seperti makan makanan sehat, berolahraga secara rutin, dan tidur yang cukup.",
                "https://i.pinimg.com/564x/21/d8/3e/21d83e8bfeb158e566610ef0bd622610.jpg"),

            Article("Makanan Sehat",
                "Makanan sehat adalah kunci untuk tubuh yang sehat. Pelajari berbagai jenis makanan yang baik untuk tubuh kita, seperti buah-buahan, sayuran, dan biji-bijian, serta bagaimana makanan ini membantu kita merasa lebih baik dan lebih energik.",
                "https://i.pinimg.com/736x/1c/bf/3e/1cbf3e3586d13e5f3e33a74540eddb5b.jpg"),

            Article("Sejarah Televisi",
                "Televisi telah menjadi bagian penting dari kehidupan kita. Pelajari bagaimana televisi ditemukan, bagaimana teknologi televisi berkembang dari waktu ke waktu, dan bagaimana televisi digunakan untuk mendapatkan informasi dan hiburan.",
                "https://asset.kompas.com/crops/qWqbJ53KDnJ4MUrpkLMLNKTofew=/0x82:640x509/750x500/data/photo/2022/11/03/63635e408a785.jpeg"),

            Article("Penemuan Lampu",
                "Penemuan lampu telah mengubah cara kita hidup. Temukan cerita tentang bagaimana lampu ditemukan, bagaimana lampu berkembang seiring waktu, dan bagaimana lampu membantu kita melakukan aktivitas sehari-hari dengan lebih baik, terutama di malam hari.",
                "https://asset-a.grid.id//crop/112x0:561x504/700x0/photo/2019/12/03/4255448458.jpg"),

            Article("Kesehatan Gigi",
                "Gigi yang sehat penting untuk kesehatan secara keseluruhan. Pelajari cara merawat gigi dengan baik, tips untuk menjaga gigi tetap kuat dan bersih, serta bagaimana kebiasaan baik dalam merawat gigi dapat membantu kita terhindar dari masalah gigi.",
                "https://rsjakarta.co.id/wp-content/uploads/2022/03/images.png"),

            Article("Sejarah Sepeda",
                "Sepeda adalah alat transportasi yang telah digunakan sejak lama. Temukan bagaimana sepeda ditemukan, bagaimana sepeda telah berubah dan berkembang dari waktu ke waktu, dan bagaimana sepeda masih digunakan hingga saat ini sebagai alat transportasi yang efisien.",
                "https://awsimages.detik.net.id/community/media/visual/2020/07/02/foto-sejarah-sepeda-3_169.jpeg?w=600&q=90")
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

        // Menerapkan animasi pada RecyclerView
        binding.itemKu.alpha = 0f
        binding.itemKu.visibility = View.VISIBLE
        binding.itemKu.animate()
            .alpha(1f)
            .setDuration(1000)
            .setStartDelay(200)
            .start()
    }
}

