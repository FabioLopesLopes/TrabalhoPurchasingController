package com.example.computacaomovel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenu : AppCompatActivity() {
    private lateinit var logoutPage: Button;
    private lateinit var createExpense: Button;
    private lateinit var createStudy: Button;
    private lateinit var addExpense: Button;
    private lateinit var addStudy: Button;
    private lateinit var expenseHistory: Button;
    private lateinit var studyHistory: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        supportActionBar?.hide();
        logoutPage = findViewById(R.id.bt_logoutMain);
        createExpense = findViewById(R.id.bt_createExpense);
        createStudy = findViewById(R.id.bt_createStudy);
        addExpense = findViewById(R.id.bt_addExpense);
        addStudy = findViewById(R.id.bt_addStudy);
        expenseHistory = findViewById(R.id.bt_expenseHistory);
        studyHistory = findViewById(R.id.bt_studyHistory);

        logoutPage.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent);
            finish();
        }

        createExpense.setOnClickListener {
            val intent = Intent(this, CreateExpense::class.java)
            startActivity(intent);
            finish();
        }

        createStudy.setOnClickListener {
            val intent = Intent(this, CreateStudy::class.java)
            startActivity(intent);
            finish();
        }

        addStudy.setOnClickListener {
            val intent = Intent(this, ListStudy::class.java)

            startActivity(intent);
            finish();
        }

        addExpense.setOnClickListener {
            val intent = Intent(this, ListExpense::class.java)

            startActivity(intent);
            finish();
        }

        studyHistory.setOnClickListener {
            val intent = Intent(this, StudyHistory::class.java)

            startActivity(intent);
            finish();
        }

        expenseHistory.setOnClickListener {
            val intent = Intent(this, ExpenseHistory::class.java)
            startActivity(intent);
            finish();
        }
    }
}