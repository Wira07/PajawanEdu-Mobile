package com.wira_fkom.pajawanedumobile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wira_fkom.pajawanedumobile.R
import com.wira_fkom.pajawanedumobile.data.Article
import com.wira_fkom.pajawanedumobile.databinding.ItemLayoutBinding

class ArticleAdapter(
    private val articles: List<Article>,
    private val onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvItemName.text = article.title
            binding.tvItemDescription.text = article.description

            Glide.with(binding.root.context)
                .load(article.imageUrl)
                .placeholder(R.drawable.placeholder) // Placeholder image
                .error(R.drawable.error) // Error image
                .into(binding.imgItemPhoto)

            val isFavorite = FavoriteManager.isFavorite(binding.root.context, article)
            binding.btnFavorite.setImageResource(
                if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            )

            binding.btnFavorite.setOnClickListener {
                if (isFavorite) {
                    FavoriteManager.removeFavorite(binding.root.context, article)
                    binding.btnFavorite.setImageResource(R.drawable.ic_favorite_border)
                } else {
                    FavoriteManager.addFavorite(binding.root.context, article)
                    binding.btnFavorite.setImageResource(R.drawable.ic_favorite)
                }
            }

            binding.root.setOnClickListener {
                onItemClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size
}
