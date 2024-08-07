package com.wira_fkom.pajawanedumobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wira_fkom.pajawanedumobile.databinding.ActivityMainBinding
import com.wira_fkom.pajawanedumobile.learning.ArtikelActivity
import com.wira_fkom.pajawanedumobile.modul.BahasaActivity
import com.wira_fkom.pajawanedumobile.modul.IPSActivity
import com.wira_fkom.pajawanedumobile.modul.MatematikaActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "EduKids"

        // Get username and profile image URL from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "User")
        val profileImageUrl = sharedPreferences.getString("profileImageUrl", null)

        // Set welcome text
        binding.welcomeText.text = "Halo, $username!"

        // Display profile image if available
        profileImageUrl?.let { url ->
            Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_profile_placeholder) // Optional: Placeholder image
                .error(R.drawable.ic_profile_placeholder) // Optional: Error image
                .into(binding.profileImage) // Assuming ivProfileImage is the ID of your ImageView
        }

        setupBottomNavigation()

        // Setting click listeners for each card
        binding.cardMatematika.setOnClickListener {
            val intent = Intent(this, MatematikaActivity::class.java)
            startActivity(intent)
        }

        binding.buttonArtikel.setOnClickListener {
            val intent = Intent(this, ArtikelActivity::class.java)
            startActivity(intent)
        }

        binding.cardBahasaIndonesia.setOnClickListener {
            val intent = Intent(this, BahasaActivity::class.java)
            startActivity(intent)
        }

        binding.cardIpas.setOnClickListener {
            val intent = Intent(this, IPSActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = binding.bottomNavigation

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> true
                R.id.navigation_library -> {
                    val intent = Intent(this, QuizActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_favorite -> {
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
