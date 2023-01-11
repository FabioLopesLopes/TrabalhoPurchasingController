package com.example.computacaomovel

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.computacaomovel.Model.ExpenseEntry
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddExpense : AppCompatActivity() {

    private lateinit var value: TextView;
    private lateinit var description: TextView;
    private lateinit var expenseType: TextView;
    private lateinit var expenseMonth: TextView;
    private lateinit var back: Button;
    private lateinit var add: Button;
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)
        supportActionBar?.hide();

        val name = this.intent.getStringExtra("name")
        val month = this.intent.getStringExtra("month")

        back = findViewById(R.id.bt_back)
        add = findViewById(R.id.bt_addExpense)
        value = findViewById(R.id.expense_value)
        description = findViewById(R.id.expense_description)
        expenseMonth = findViewById(R.id.expense_month)
        expenseType = findViewById(R.id.expense_name);
        expenseType.text = name
        expenseMonth.text = month

        back.setOnClickListener {
            val intent = Intent(this, ListExpense::class.java)
            startActivity(intent);
            finish();
        }
        add.setOnClickListener { view ->
            val name = expenseType.text.toString();
            val month = expenseMonth.text.toString();
            val value = value.text.toString();
            val description = description.text.toString();

            if (value.isEmpty() || name.isEmpty() || month.isEmpty() || description.isEmpty()) {
                var snackbar: Snackbar = Snackbar.make(
                    view,
                    "The information does not meet the requirements",
                    Snackbar.LENGTH_SHORT
                );
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.RED);
                snackbar.show();
            } else {

                val expenseEntry = ExpenseEntry(name, value, description, month)
                addExpenseEntryToDatabase(expenseEntry)

                val intent = Intent(this@AddExpense, MainMenu::class.java)
                finish()
                startActivity(intent);
            }
        }
    }
    private fun addExpenseEntryToDatabase( expenseEntry: ExpenseEntry) {
        db.collection("expenseEntry").add(expenseEntry)
            .addOnSuccessListener { documentReference ->
            Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }
}