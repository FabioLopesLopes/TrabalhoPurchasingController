package com.example.computacaomovel.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.computacaomovel.Model.Study
import com.example.computacaomovel.databinding.StudyItemBinding

class AdapterStudies(
    private val context: Context,
    private val listStudies: List<Study>,
    private val onItemClick: (Study) -> Unit
) :
    RecyclerView.Adapter<AdapterStudies.StudyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder {
        val listItem = StudyItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return StudyViewHolder(listItem);
    }

    override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {
       holder.bind(item = listStudies[position])
    }

    override fun getItemCount() = listStudies.size;

    inner class StudyViewHolder(private val binding: StudyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Study) {
            binding.root.setOnClickListener { onItemClick(item) }

            item.name.also { binding.txt.text = it }
            item.name.also { binding.txt.text = it }
        }
    }
}