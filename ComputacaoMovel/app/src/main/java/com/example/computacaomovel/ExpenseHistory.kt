package com.example.computacaomovel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.computacaomovel.Adapter.AdapterExpenseHistory
import com.example.computacaomovel.Adapter.AdapterStudyHistory
import com.example.computacaomovel.Model.ExpenseEntry
import com.example.computacaomovel.Model.StudyEntry
import com.example.computacaomovel.databinding.ActivityExpenseHistoryBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ExpenseHistory : AppCompatActivity() {
    private lateinit var binding: ActivityExpenseHistoryBinding
    private lateinit var adapterExpenseHistory: AdapterExpenseHistory
    private var history: MutableList<ExpenseEntry> = mutableListOf()
    private val db = Firebase.firestore
    private lateinit var back: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_history)
        supportActionBar?.hide();

        this.binding = ActivityExpenseHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerviewExpenses = binding.recyclerViewExpenseHist;
        recyclerviewExpenses.layoutManager = LinearLayoutManager(this)
        recyclerviewExpenses.setHasFixedSize(true)

        back = findViewById(R.id.bt_back);
        back.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent);
            finish();
        }
        db.collection("expenseEntry").get().addOnSuccessListener { res ->
            for (document in res) {
                history.add(ExpenseEntry.fromHashMap(document.data))

            }
            adapterExpenseHistory = AdapterExpenseHistory(
                this,
                history)
            recyclerviewExpenses.adapter = adapterExpenseHistory
        }
    }
}