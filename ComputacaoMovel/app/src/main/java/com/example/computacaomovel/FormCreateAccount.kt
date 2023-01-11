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
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class FormCreateAccount : AppCompatActivity() {

    private lateinit var userId: String
    private lateinit var back: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_create_account)
        supportActionBar?.hide();

        var message = listOf<String>("Fill in all fields","Registration done successfully");
        var editname: EditText = findViewById(R.id.edit_name);
        var editemail: EditText = findViewById(R.id.edit_email);
        var editpassword: EditText = findViewById(R.id.edit_password);
        var btcreate: Button = findViewById(R.id.bt_create);
        back = findViewById(R.id.bt_back);

        back.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent);
            finish();
        }

        btcreate.setOnClickListener { view ->
            var name: String = editname.text.toString();
            var email: String = editemail.text.toString();
            var password: String = editpassword.text.toString();


            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                var snackbar: Snackbar = Snackbar.make(view, message[0], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.RED);
                snackbar.show();

            } else {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            NextPage();
                            var ff: FirebaseFirestore = FirebaseFirestore.getInstance();
                            var users: HashMap<String, String> = HashMap<String, String>()
                            users.put("name", name);

                            userId = FirebaseAuth.getInstance().currentUser!!.uid;
                            var docRef: DocumentReference = ff.collection("Users").document(userId);
                            docRef.set(users)
                                .addOnSuccessListener {
                                    Log.d(
                                        ContentValues.TAG,
                                        "Requesting connection.."
                                    )
                                }
                                .addOnFailureListener { e ->
                                    Log.e(
                                        ContentValues.TAG,
                                        "Error requesting connection",
                                        e
                                    )
                                }

                        } else {

                            var error: String;
                            try {
                                throw task.exception!!;

                            } catch (e: FirebaseAuthWeakPasswordException) {
                                error = "Enter a password with a minimum of 6 characters";
                            } catch (e: FirebaseAuthUserCollisionException) {
                                error = "This account is already registered";
                            } catch (e: FirebaseAuthInvalidCredentialsException) {
                                error = "Invalid email";
                            } catch (e: Exception) {
                                error = "Registration error";
                            }
                            val snackbar: Snackbar =
                                Snackbar.make(view, error, Snackbar.LENGTH_SHORT);
                            snackbar.setBackgroundTint(Color.WHITE);
                            snackbar.setTextColor(Color.RED);
                            snackbar.show();

                        }
                    }
            }
        }

        }
    fun NextPage() {
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent);
        finish();
    }
}