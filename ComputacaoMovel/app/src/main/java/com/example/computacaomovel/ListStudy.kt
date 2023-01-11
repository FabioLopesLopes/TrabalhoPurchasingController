package com.example.computacaomovel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.computacaomovel.Adapter.AdapterStudies
import com.example.computacaomovel.Model.Study
import com.example.computacaomovel.databinding.ActivityListStudyBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListStudy : AppCompatActivity() {

    private lateinit var binding: ActivityListStudyBinding
    private lateinit var adapterStudies: AdapterStudies
    private var studies: MutableList<Study> = mutableListOf()
    private val db = Firebase.firestore
    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = ActivityListStudyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerviewStudies = binding.recyclerViewStudies;
        recyclerviewStudies.layoutManager = LinearLayoutManager(this)
        recyclerviewStudies.setHasFixedSize(true)

        back = findViewById(R.id.bt_back);
        back.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent);
            finish();
        }

        db.collection("study").get().addOnSuccessListener { res ->
            for (document in res) {
                studies.add(Study.fromHashMap(document.data))
            }
            adapterStudies = AdapterStudies(
                this,
                studies,
                onItemClick = {
                    val intent = Intent(this, AddStudy::class.java)
                    intent.putExtra("name","${it.name}")
                    startActivity(intent); })
            recyclerviewStudies.adapter = adapterStudies
        }
    }

}