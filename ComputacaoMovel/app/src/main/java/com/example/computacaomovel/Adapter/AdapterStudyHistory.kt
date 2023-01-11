package com.example.computacaomovel.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.computacaomovel.Model.StudyEntry
import com.example.computacaomovel.databinding.StudyHistoryBinding


class AdapterStudyHistory (
    private val context: Context,
    private val listStudyHistory: List<StudyEntry>

) :
    RecyclerView.Adapter<AdapterStudyHistory.StudyHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyHistoryViewHolder {

        val listItem = StudyHistoryBinding.inflate(LayoutInflater.from(context), parent, false);
        return StudyHistoryViewHolder(listItem);
    }

    override fun onBindViewHolder(holder: StudyHistoryViewHolder, position: Int) {
        holder.bind(item = listStudyHistory[position])
    }
    override fun getItemCount() = listStudyHistory.size;
    inner class StudyHistoryViewHolder(private val binding: StudyHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StudyEntry) {
            item.name.also { binding.name.text = it }
            item.storeName.also { binding.storeName.text = it }
            item.value.also { binding.value.text = it }
        }
    }
}