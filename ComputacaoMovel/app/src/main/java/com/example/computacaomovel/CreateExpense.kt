package com.example.computacaomovel

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.computacaomovel.Model.Expense
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateExpense : AppCompatActivity() {
    private lateinit var back: Button;
    private lateinit var create: Button;
    private lateinit var nameExpense: EditText;
    private lateinit var monthExpense: EditText;
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_expense)
        supportActionBar?.hide();

        back = findViewById(R.id.bt_back);
        create = findViewById(R.id.bt_createExpense);
        nameExpense = findViewById(R.id.expense_name);
        monthExpense = findViewById(R.id.expense_month);
        db = FirebaseFirestore.getInstance()

        back.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent);
            finish();
        }

        create.setOnClickListener { view ->
            var name = nameExpense.text.toString();
            var value = monthExpense.text.toString();


            if (name.isEmpty() || value.isEmpty()) {
                var snackbar: Snackbar = Snackbar.make(
                    view,
                    "The information does not meet the requirements",
                    Snackbar.LENGTH_SHORT
                );
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.RED);
                snackbar.show();
            } else {
                val expense = Expense(name, value);
                addExpenseToDatabase(expense);

                val intent = Intent(this@CreateExpense, MainMenu::class.java)
                finish();
                startActivity(intent);
            }
        }
    }
    private fun addExpenseToDatabase(expense: Expense) {
        db.collection("expense").add(expense)
            .addOnSuccessListener { documentReference ->
            Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }
}

