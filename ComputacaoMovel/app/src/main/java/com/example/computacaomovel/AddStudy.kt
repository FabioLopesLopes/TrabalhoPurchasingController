package com.example.computacaomovel

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.computacaomovel.Model.Expense
import com.example.computacaomovel.Model.Study
import com.example.computacaomovel.Model.StudyEntry
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddStudy : AppCompatActivity() {

    private lateinit var value: TextView
    private lateinit var storeName: TextView
    private lateinit var nameStudy: TextView
    private lateinit var back: Button
    private lateinit var add: Button
    private lateinit var soma: Number
    private lateinit var n: Number
    private lateinit var media: Number
    private var listStudy:  MutableList<StudyEntry> = mutableListOf()
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_study)
        supportActionBar?.hide();
        val name = intent.getStringExtra("name")

        storeName = findViewById(R.id.study_Store_Name);
        value = findViewById(R.id.study_value);
        back = findViewById(R.id.bt_back);
        add = findViewById(R.id.bt_addStudy);
        nameStudy = findViewById(R.id.study_name1);
        nameStudy.text = name

        back.setOnClickListener {
            val intent = Intent(this, ListStudy::class.java)
            startActivity(intent);
            finish();
        }
        add.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {

                val name = nameStudy.text.toString();
                val storeName = storeName.text.toString();
                val value = value.text.toString();

                if (storeName.isEmpty() || value.isEmpty() || name.isEmpty()) {
                    var snackbar: Snackbar = Snackbar.make(view,
                        "The information does not meet the requirements",
                        Snackbar.LENGTH_SHORT
                    );
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.RED);
                    snackbar.show();
                } else {
                    val studyEntry = StudyEntry(name,storeName,value)
                    addStudyEntryToDatabase(studyEntry)

                    val intent = Intent(this@AddStudy, MainMenu::class.java)
                    finish()
                    startActivity(intent);
                }
            }
        })
    }
    private fun addStudyEntryToDatabase( studyEntry: StudyEntry) {
        db.collection("studyEntry").add(studyEntry)
            .addOnSuccessListener { documentReference ->
            Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }
}

