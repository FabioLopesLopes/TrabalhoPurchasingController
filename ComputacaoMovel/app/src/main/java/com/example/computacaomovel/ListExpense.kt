package com.example.computacaomovel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.computacaomovel.Model.Expense
import com.example.computacaomovel.databinding.ActivityListExpenseBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.computacaomovel.Adapter.AdapterExpense

class ListExpense : AppCompatActivity() {

    private lateinit var binding: ActivityListExpenseBinding
    private lateinit var adapterExpense: AdapterExpense
    private var expenses: MutableList<Expense> = mutableListOf()
    private val db = Firebase.firestore;
    private lateinit var back: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide();
        binding = ActivityListExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerviewExpenses = binding.recyclerViewExpenses;
        recyclerviewExpenses.layoutManager = LinearLayoutManager(this)
        recyclerviewExpenses.setHasFixedSize(true)


        back = findViewById(R.id.bt_back);
        back.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent);
            finish();
        }

        db.collection("expense").get().addOnSuccessListener { res ->
            for (document in res) {
                expenses.add(Expense.fromHashMap(document.data))

            }
            adapterExpense = AdapterExpense(
                this,
                expenses,
                onItemClick = {
                    val intent = Intent(this, AddExpense::class.java)
                    intent.putExtra("name","${it.name}")
                    intent.putExtra("month","${it.month}")
                    startActivity(intent); })
            recyclerviewExpenses.adapter = adapterExpense
        }
    }

}
