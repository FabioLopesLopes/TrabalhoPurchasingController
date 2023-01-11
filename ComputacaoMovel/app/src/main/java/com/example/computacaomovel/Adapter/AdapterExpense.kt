package com.example.computacaomovel.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.computacaomovel.Model.Expense
import com.example.computacaomovel.databinding.ExpenseItemBinding

class AdapterExpense(
    private val context: Context,
    private val listExpense: List<Expense>,
    private val onItemClick: (Expense) -> Unit
) :
    RecyclerView.Adapter<AdapterExpense.ExpenseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val listItem = ExpenseItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return ExpenseViewHolder(listItem);
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(item = listExpense[position])
    }

    override fun getItemCount() = listExpense.size;

    inner class ExpenseViewHolder(private val binding: ExpenseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Expense) {
            binding.root.setOnClickListener { onItemClick(item) }

            item.name.also { binding.txt.text = it }
            item.month.also { binding.month.text = it }
        }
    }
}