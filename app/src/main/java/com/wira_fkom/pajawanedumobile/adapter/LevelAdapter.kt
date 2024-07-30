package com.wira_fkom.pajawanedumobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wira_fkom.pajawanedumobile.R

// ViewHolder class for holding the view for each item
class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val levelName: TextView = itemView.findViewById(R.id.txtLevelName)
}

// Adapter class for the RecyclerView
class LevelAdapter(
    private val levels: List<Level>,
    private val onItemClick: (Level) -> Unit
) : RecyclerView.Adapter<LevelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        // Inflate the item layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.level_layout, parent, false)
        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        // Bind the data to the view holder
        val level = levels[position]
        holder.levelName.text = level.name

        // Set an item click listener
        holder.itemView.setOnClickListener {
            onItemClick(level)
        }
    }

    override fun getItemCount(): Int {
        return levels.size
    }
}
