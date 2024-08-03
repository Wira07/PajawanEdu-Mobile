package com.wira_fkom.pajawanedumobile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.wira_fkom.pajawanedumobile.databinding.ActivityProfileBinding
import com.wira_fkom.pajawanedumobile.login.LoginActivity
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
            val name = it.displayName ?: "" // Default to empty string if null
            val email = it.email ?: "" // Default to empty string if null

            binding.tvName.text = Editable.Factory.getInstance().newEditable(name)
            binding.tvEmail.text = Editable.Factory.getInstance().newEditable(email)

            // Load profile image if available
            loadProfileImage(user.uid)
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
                    val intent = Intent(this, QuizActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_favorite -> {
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_profile -> true
                else -> false
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            displayLocalImage()
            uploadImage()
        }
    }

    private fun displayLocalImage() {
        // Display the selected image locally using Glide
        imageUri?.let { uri ->
            Glide.with(this)
                .load(uri)
                .placeholder(R.drawable.ic_profile_placeholder)
                .error(R.drawable.ic_profile_placeholder)
                .into(binding.ivProfileImage)
        }
    }

    private fun uploadImage() {
        imageUri?.let { uri ->
            val storageRef = storage.reference.child("profileImages/${UUID.randomUUID()}")
            val uploadTask = storageRef.putFile(uri)

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
                // Log the download URI
                Log.d("ProfileActivity", "Image URL: $downloadUri")

                // Update UI with the new image using Glide
                Glide.with(this)
                    .load(downloadUri)
                    .placeholder(R.drawable.ic_profile_placeholder) // Optional: Placeholder image
                    .error(R.drawable.ic_profile_placeholder) // Optional: Error image
                    .into(binding.ivProfileImage)
            }
            .addOnFailureListener { e ->
                // Handle the error
                e.printStackTrace()
            }
    }

    private fun loadProfileImage(userId: String) {
        val userDocRef = firestore.collection("users").document(userId)
        userDocRef.get().addOnSuccessListener { document ->
            val profileImageUrl = document.getString("profileImageUrl")
            if (profileImageUrl != null) {
                // Log the profile image URL
                Log.d("ProfileActivity", "Profile Image URL: $profileImageUrl")

                Glide.with(this)
                    .load(profileImageUrl)
                    .placeholder(R.drawable.ic_profile_placeholder)
                    .error(R.drawable.ic_profile_placeholder)
                    .into(binding.ivProfileImage)
            }
        }
    }
}

