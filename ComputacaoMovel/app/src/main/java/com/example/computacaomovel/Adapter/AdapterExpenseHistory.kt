package com.example.computacaomovel.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.computacaomovel.Model.ExpenseEntry
import com.example.computacaomovel.databinding.ExpenseHistoryBinding

class AdapterExpenseHistory (private val context: Context,
    private val listExpenseHistory: List<ExpenseEntry>

    ) :
    RecyclerView.Adapter<AdapterExpenseHistory.ExpenseHistoryViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseHistoryViewHolder {

            val listItem = ExpenseHistoryBinding.inflate(LayoutInflater.from(context), parent, false);
            return ExpenseHistoryViewHolder(listItem);
        }

        override fun onBindViewHolder(holder: ExpenseHistoryViewHolder, position: Int) {
            holder.bind(item = listExpenseHistory[position])
        }

        override fun getItemCount() = listExpenseHistory.size;
        inner class ExpenseHistoryViewHolder(private val binding: ExpenseHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: ExpenseEntry) {
                item.name.also { binding.name.text = it }
                item.value.also { binding.value.text = it }
                item.month.also { binding.month.text = it }
                item.description.also { binding.description.text = it }
            }
        }
    }