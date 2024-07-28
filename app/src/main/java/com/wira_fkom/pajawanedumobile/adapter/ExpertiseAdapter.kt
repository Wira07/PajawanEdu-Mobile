package com.wira_fkom.pajawanedumobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wira_fkom.pajawanedumobile.databinding.ItemExpertiseBinding

class ExpertiseAdapter(
    private val expertises: List<String>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ExpertiseAdapter.ExpertiseViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(expertise: String)
    }

    inner class ExpertiseViewHolder(private val binding: ItemExpertiseBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(expertise: String) {
            binding.expertiseText.text = expertise
        }

        override fun onClick(v: View?) {
            val expertise = expertises[adapterPosition]
            itemClickListener.onItemClick(expertise)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpertiseViewHolder {
        val binding = ItemExpertiseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpertiseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpertiseViewHolder, position: Int) {
        holder.bind(expertises[position])
    }

    override fun getItemCount() = expertises.size
}
