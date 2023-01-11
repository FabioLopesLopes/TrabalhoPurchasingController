package com.example.computacaomovel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.computacaomovel.Adapter.AdapterStudyHistory
import com.example.computacaomovel.Model.StudyEntry
import com.example.computacaomovel.databinding.ActivityStudyHistoryBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class StudyHistory : AppCompatActivity() {
    private lateinit var binding: ActivityStudyHistoryBinding
    private lateinit var adapterStudyHistory: AdapterStudyHistory
    private var history: MutableList<StudyEntry> = mutableListOf()
    private val db = Firebase.firestore
    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        this.binding = ActivityStudyHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerviewStudies = binding.recyclerViewStudyHist;
        recyclerviewStudies.layoutManager = LinearLayoutManager(this)
        recyclerviewStudies.setHasFixedSize(true)

        back = findViewById(R.id.bt_back);
        back.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent);
            finish();
        }
        db.collection("studyEntry").get().addOnSuccessListener { res ->
            for (document in res) {
                history.add(StudyEntry.fromHashMap(document.data))

            }
            adapterStudyHistory = AdapterStudyHistory(
                this,
                history)
            recyclerviewStudies.adapter = adapterStudyHistory
        }
    }

    private fun  filter(e: String) {
        val filteredItem = ArrayList<StudyEntry>()

        for(item in history) {
            if(item.storeName!!.lowercase().contains(e.lowercase())){
                filteredItem.add(item)
            }
        }
        adapterStudyHistory = AdapterStudyHistory(
            this,
            filteredItem)
    }
}