package com.wira_fkom.pajawanedumobile.learning

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable the Up button

        val articles = listOf(
            Article(
                title = "Apa Itu Komputer?",
                description = "Komputer adalah alat yang sangat kompleks dan multifungsi yang telah menjadi bagian tak terpisahkan dari kehidupan modern. Dalam artikel ini, kita akan mengeksplorasi lebih dalam tentang apa itu komputer, mulai dari sejarah singkatnya, evolusinya dari alat hitung sederhana menjadi mesin canggih yang dapat menjalankan miliaran instruksi per detik. Kita juga akan membahas bagaimana komputer bekerja, termasuk komponen-komponen utama seperti CPU, RAM, dan penyimpanan, serta bagaimana semua komponen ini bekerja bersama untuk menjalankan berbagai tugas. Selain itu, artikel ini juga akan menjelaskan bagaimana komputer telah menjadi alat yang sangat penting dalam berbagai aspek kehidupan, mulai dari pendidikan, hiburan, hingga pekerjaan, serta bagaimana komputer terus berkembang dengan kecepatan yang luar biasa untuk memenuhi kebutuhan manusia yang semakin kompleks.",
                imageUrl = "https://i.pinimg.com/736x/0f/f1/67/0ff167f2ffba09f6337591aa6b0b694d.jpg"
            ),
            Article(
                title = "Internet untuk Anak-anak",
                description = "Internet adalah jaringan global yang menghubungkan miliaran perangkat di seluruh dunia, memungkinkan komunikasi dan pertukaran informasi secara real-time. Artikel ini dirancang khusus untuk anak-anak, dengan tujuan untuk memberikan pemahaman dasar tentang internet, bagaimana cara menggunakannya dengan bijak dan aman. Kita akan membahas tentang manfaat internet, seperti akses ke informasi pendidikan, hiburan, dan komunikasi dengan teman dan keluarga yang jauh. Selain itu, kita juga akan mengeksplorasi potensi bahaya yang mungkin ditemui di internet, seperti cyberbullying, konten yang tidak pantas, dan pentingnya menjaga privasi online. Melalui panduan ini, anak-anak akan diajarkan cara menggunakan internet secara bertanggung jawab dan bagaimana melindungi diri mereka dari risiko yang ada di dunia maya.",
                imageUrl = "https://cdn.pixabay.com/photo/2015/06/24/15/45/keyboard-820274_960_720.jpg"
            ),
            Article(
                title = "Robot yang Membantu",
                description = "Robot adalah perangkat mekanis yang dapat melakukan tugas-tugas tertentu secara otomatis atau dengan sedikit intervensi manusia. Dalam artikel ini, kita akan mendalami dunia robotika, mulai dari sejarah penemuan robot hingga peran penting mereka dalam kehidupan modern. Kita akan melihat bagaimana robot digunakan di berbagai sektor, seperti rumah tangga untuk membantu pekerjaan sehari-hari seperti membersihkan dan memasak, hingga di industri dan medis. Di rumah sakit, misalnya, robot dapat melakukan operasi yang sangat presisi, membantu dokter dan tenaga medis dalam berbagai prosedur yang kompleks. Kita juga akan membahas masa depan robotika, dengan prediksi tentang bagaimana robot mungkin akan semakin cerdas dan otonom, serta dampaknya terhadap kehidupan kita.",
                imageUrl = "https://i.pinimg.com/564x/08/6c/32/086c32533bc3dedbda109b96136f2e22.jpg"
            ),
            Article(
                title = "Telepon Pintar",
                description = "Telepon pintar atau smartphone adalah salah satu inovasi teknologi paling revolusioner dalam beberapa dekade terakhir. Artikel ini akan membawa kita melalui perjalanan perkembangan telepon pintar, dari telepon seluler pertama yang hanya bisa melakukan panggilan dan mengirim pesan teks, hingga perangkat multifungsi yang kita miliki saat ini. Kita akan membahas berbagai fitur yang ditawarkan oleh smartphone modern, seperti kemampuan untuk mengambil foto berkualitas tinggi, mengakses internet, bermain game, dan menggunakan aplikasi yang membantu dalam berbagai aspek kehidupan. Selain itu, kita juga akan mengeksplorasi bagaimana smartphone telah mengubah cara kita berkomunikasi, bekerja, dan bersosialisasi, serta bagaimana teknologi ini terus berkembang dengan fitur-fitur baru yang semakin canggih.",
                imageUrl = "https://i.pinimg.com/564x/ea/7e/18/ea7e18a2d223c07800a6a3a3b9268589.jpg"
            ),
            Article(
                title = "Menjaga Kesehatan",
                description = "Kesehatan adalah aset paling berharga yang kita miliki, dan menjaga kesehatan tubuh adalah salah satu tanggung jawab paling penting yang kita miliki. Artikel ini akan memberikan panduan komprehensif tentang berbagai cara untuk menjaga kesehatan tubuh. Kita akan membahas pentingnya pola makan yang sehat dan seimbang, yang mencakup asupan buah-buahan, sayuran, protein, dan karbohidrat yang cukup. Selain itu, kita akan melihat pentingnya olahraga rutin untuk menjaga kebugaran dan mengurangi risiko penyakit. Tidur yang cukup juga akan dibahas sebagai faktor penting dalam menjaga kesehatan fisik dan mental. Artikel ini akan memberikan tips praktis yang dapat diterapkan dalam kehidupan sehari-hari untuk membantu menjaga kesehatan tubuh secara keseluruhan.",
                imageUrl = "https://i.pinimg.com/564x/21/d8/3e/21d83e8bfeb158e566610ef0bd622610.jpg"
            ),
            Article(
                title = "Makanan Sehat",
                description = "Makanan sehat adalah kunci utama untuk menjaga tubuh tetap sehat dan berfungsi optimal. Artikel ini akan membahas secara mendalam tentang berbagai jenis makanan yang baik untuk tubuh kita, termasuk buah-buahan, sayuran, biji-bijian, dan protein nabati maupun hewani. Kita akan menjelajahi manfaat dari setiap jenis makanan ini, seperti bagaimana buah-buahan dapat memberikan vitamin dan serat yang diperlukan tubuh, serta bagaimana sayuran membantu menjaga sistem pencernaan yang sehat. Selain itu, artikel ini juga akan memberikan tips tentang cara memilih makanan yang sehat di tengah banyaknya pilihan makanan olahan yang kurang sehat di pasaran. Dengan informasi ini, pembaca akan lebih memahami pentingnya pola makan yang sehat dan bagaimana membuat pilihan makanan yang lebih baik untuk kesehatan jangka panjang.",
                imageUrl = "https://i.pinimg.com/736x/1c/bf/3e/1cbf3e3586d13e5f3e33a74540eddb5b.jpg"
            ),
            Article(
                title = "Sejarah Televisi",
                description = "Televisi adalah salah satu alat komunikasi massa yang paling berpengaruh di abad ke-20 dan ke-21. Artikel ini akan mengajak pembaca untuk melihat perjalanan sejarah televisi, dari penemuan awalnya hingga bagaimana teknologi ini telah berevolusi menjadi media hiburan dan informasi yang sangat populer. Kita akan melihat bagaimana televisi pertama kali ditemukan, bagaimana siaran hitam-putih berkembang menjadi siaran warna, dan bagaimana teknologi televisi terus berkembang dengan hadirnya televisi digital dan smart TV yang dapat terhubung ke internet. Selain itu, kita juga akan membahas dampak sosial dari televisi, bagaimana televisi telah mempengaruhi budaya populer, serta perannya dalam menyebarkan informasi dan hiburan ke seluruh dunia.",
                imageUrl = "https://asset.kompas.com/crops/qWqbJ53KDnJ4MUrpkLMLNKTofew=/0x82:640x509/750x500/data/photo/2022/11/03/63635e408a785.jpeg"
            ),
            Article(
                title = "Penemuan Lampu",
                description = "Penemuan lampu adalah salah satu penemuan terpenting dalam sejarah manusia, yang telah mengubah cara kita hidup, bekerja, dan bermain. Artikel ini akan membawa pembaca melalui sejarah penemuan lampu, mulai dari lilin dan lampu minyak yang digunakan di zaman kuno, hingga penemuan lampu pijar oleh Thomas Edison yang merevolusi penerangan di seluruh dunia. Kita akan melihat bagaimana teknologi lampu terus berkembang, dari lampu pijar ke lampu neon, dan kemudian ke lampu LED yang lebih hemat energi dan ramah lingkungan. Selain itu, kita juga akan membahas bagaimana lampu telah mempengaruhi kehidupan kita sehari-hari, membuat kita dapat bekerja lebih produktif di malam hari dan menciptakan suasana yang lebih nyaman di rumah.",
                imageUrl = "https://asset-a.grid.id//crop/112x0:561x504/700x0/photo/2019/12/03/4255448458.jpg"
            ),
            Article(
                title = "Kesehatan Gigi",
                description = "Gigi yang sehat adalah fondasi dari kesehatan secara keseluruhan. Artikel ini akan memberikan panduan lengkap tentang cara merawat gigi dengan baik, mulai dari teknik menyikat gigi yang benar, pentingnya menggunakan benang gigi, hingga cara memilih makanan yang baik untuk kesehatan gigi. Kita juga akan membahas berbagai masalah gigi yang umum, seperti gigi berlubang, penyakit gusi, dan cara mencegahnya. Selain itu, artikel ini akan memberikan tips tentang bagaimana membiasakan diri untuk melakukan pemeriksaan gigi secara rutin ke dokter gigi, dan bagaimana kebiasaan baik dalam merawat gigi dapat membantu kita memiliki senyum yang indah dan gigi yang kuat sepanjang hidup.",
                imageUrl = "https://rsjakarta.co.id/wp-content/uploads/2022/03/images.png"
            ),
            Article(
                title = "Sejarah Sepeda",
                description = "Sepeda adalah salah satu alat transportasi paling populer dan efisien di dunia. Artikel ini akan membawa kita melalui perjalanan panjang sejarah sepeda, dari penemuan awalnya hingga berbagai inovasi yang telah membuat sepeda menjadi alat transportasi yang sangat digemari hingga saat ini. Kita akan melihat bagaimana sepeda pertama kali ditemukan sebagai alat transportasi sederhana yang tidak memiliki pedal, hingga menjadi sepeda modern dengan berbagai fitur canggih. Selain itu, kita juga akan mengeksplorasi bagaimana sepeda digunakan di berbagai bagian dunia, baik sebagai alat transportasi harian maupun sebagai alat olahraga dan rekreasi. Dengan membaca artikel ini, pembaca akan memahami pentingnya sepeda dalam kehidupan manusia dan bagaimana sepeda terus berkembang sebagai alat transportasi yang ramah lingkungan.",
                imageUrl = "https://awsimages.detik.net.id/community/media/visual/2020/07/02/foto-sejarah-sepeda-3_169.jpeg?w=600&q=90"
            )
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
