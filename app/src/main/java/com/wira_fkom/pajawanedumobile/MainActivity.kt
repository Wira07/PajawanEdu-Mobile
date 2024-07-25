package com.wira_fkom.pajawanedumobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wira_fkom.pajawanedumobile.databinding.ActivityMainBinding
import com.wira_fkom.pajawanedumobile.modul.BahasaActivity
import com.wira_fkom.pajawanedumobile.modul.IPAActivity
import com.wira_fkom.pajawanedumobile.modul.IPSActivity
import com.wira_fkom.pajawanedumobile.modul.MatematikaActivity
import com.wira_fkom.pajawanedumobile.modul.PKNActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Smart Pajawan"

        setupBottomNavigation()

        // Setting click listeners for each card
        binding.cardMatematika.setOnClickListener {
            val intent = Intent(this, MatematikaActivity::class.java)
            startActivity(intent)
        }

        binding.cardIpa.setOnClickListener {
            val intent = Intent(this, IPAActivity::class.java)
            startActivity(intent)
        }

        binding.cardIps.setOnClickListener {
            val intent = Intent(this, IPSActivity::class.java)
            startActivity(intent)
        }

        binding.cardBahasa.setOnClickListener {
            val intent = Intent(this, BahasaActivity::class.java)
            startActivity(intent)
        }

        binding.cardPkn.setOnClickListener {
            val intent = Intent(this, PKNActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = binding.bottomNavigation

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> true
                R.id.navigation_library -> {
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_favorite -> {
                    val intent = Intent(this, LibraryActivity::class.java)
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