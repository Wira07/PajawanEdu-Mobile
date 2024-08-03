package com.wira_fkom.pajawanedumobile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.wira_fkom.pajawanedumobile.databinding.ActivityProfileBinding
import com.wira_fkom.pajawanedumobile.login.LoginActivity
import com.wira_fkom.pajawanedumobile.QuizActivity
import java.util.UUID

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth
    private val PICK_IMAGE_REQUEST = 71
    private var imageUri: Uri? = null
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "EduKids"

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
        val user = auth.currentUser

        setupBottomNavigation()

        user?.let {
            val name = it.displayName
            val email = it.email

            binding.tvName.text = "Name: $name"
            binding.tvEmail.text = "Email: $email"
        }

        // Add click action for the logout button
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Add click action for the upload image button
        binding.btnUploadImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = binding.bottomNavigation

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_library -> {
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_favorite -> {
                    val intent = Intent(this, QuizActivity::class.java)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            uploadImage()
        }
    }

    private fun uploadImage() {
        if (imageUri != null) {
            val storageRef = storage.reference.child("profileImages/${UUID.randomUUID()}")
            val uploadTask = storageRef.putFile(imageUri!!)

            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                storageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    saveImageUriToFirestore(downloadUri.toString())
                } else {
                    // Handle failures
                    task.exception?.let {
                        it.printStackTrace()
                    }
                }
            }
        }
    }

    private fun saveImageUriToFirestore(downloadUri: String) {
        val user = auth.currentUser
        val userDocRef = firestore.collection("users").document(user?.uid ?: "")

        userDocRef.update("profileImageUrl", downloadUri)
            .addOnSuccessListener {
                // Update UI with the new image
                binding.ivProfileImage.setImageURI(imageUri)
            }
            .addOnFailureListener { e ->
                // Handle the error
                e.printStackTrace()
            }
    }
}
