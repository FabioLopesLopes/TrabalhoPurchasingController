package com.example.computacaomovel

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.computacaomovel.Model.Study
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateStudy : AppCompatActivity() {

    private lateinit var back: Button;
    private lateinit var create: Button;
    private lateinit var nameStudy: EditText;
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_study)
        supportActionBar?.hide();

        back = findViewById(R.id.bt_back);
        create = findViewById(R.id.bt_createStudy);
        nameStudy = findViewById(R.id.study_name);
        db = FirebaseFirestore.getInstance()

        back.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent);
            finish();
        }

        create.setOnClickListener { view ->
            val name = nameStudy.text.toString();

            if (name.isEmpty()) {
                var snackbar: Snackbar = Snackbar.make(
                    view,
                    "The information does not meet the requirements",
                    Snackbar.LENGTH_SHORT
                );
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.RED);
                snackbar.show();
            } else {
                val study = Study(name);
                addStudyToDatabase(study);

                val intent = Intent(this@CreateStudy, MainMenu::class.java)
                finish()
                startActivity(intent);
            }
        }
    }

    private fun addStudyToDatabase(study: Study) {
        db.collection("study").add(study)
            .addOnSuccessListener { documentReference ->
            Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }
}