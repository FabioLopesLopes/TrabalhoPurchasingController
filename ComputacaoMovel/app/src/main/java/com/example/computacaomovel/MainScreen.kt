package com.example.computacaomovel

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.Nullable
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.*


class MainScreen : AppCompatActivity() {

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var userId: String
    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        supportActionBar?.hide()

        name = findViewById(R.id.user_name)
        email=  findViewById(R.id.user_email)
        button= findViewById(R.id.bt_logout)

        button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        var docRef: DocumentReference = db.collection("users").document(userId)
        docRef.addSnapshotListener { value, error ->
            if (value != null) {
                name.text = value.getString("name")
            }
        }
    }
}