package com.example.computacaomovel

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class FormLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login);
        supportActionBar?.hide();
        var message = listOf<String>("Fill in all fields", "Login done successfully");
        val editEmail: EditText = findViewById(R.id.edit_email);
        val editPassword: EditText = findViewById(R.id.edit_password);
        val btSignin: Button = findViewById(R.id.bt_signin);
        val register: TextView = findViewById(R.id.textCreate);

        register.setOnClickListener {
            val intent = Intent(this, FormCreateAccount::class.java)
            startActivity(intent);
        }
        btSignin.setOnClickListener (object : View.OnClickListener {
            override fun  onClick(view: View) {

                val email = editEmail.text.toString();
                val password: String = editPassword.text.toString();


                if (email.isEmpty() || password.isEmpty()) {
                    val snackbar: Snackbar = Snackbar.make(view, message[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.RED);
                    snackbar.show();
                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                NextPage();
                            } else {
                                var error: String;
                                try {
                                    throw task.exception!!;

                                } catch (e: Exception) {
                                    error = "Login error";
                                }

                                var snackbar: Snackbar =
                                    Snackbar.make(view, error, Snackbar.LENGTH_SHORT);
                                snackbar.setBackgroundTint(Color.WHITE);
                                snackbar.setTextColor(Color.RED);
                                snackbar.show();
                            }
                        }
                }
            }
        })
    }

    override fun onStart() {
        super.onStart();
        var currentuser = FirebaseAuth.getInstance().currentUser;
         if(currentuser != null){
             MainScreen();
         }
    }

    fun NextPage() {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent);
        finish();
    }
}
