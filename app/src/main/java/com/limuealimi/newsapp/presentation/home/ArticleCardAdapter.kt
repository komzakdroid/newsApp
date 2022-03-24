package com.limuealimi.newsapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.databinding.ArticleItemLayoutBinding
import org.w3c.dom.CharacterData

class ArticleCardAdapter :
    PagingDataAdapter<Article, ArticleCardAdapter.ArticleVh>(DiffUtilCallBack()) {

    private var events = ArrayList<Article>()

    inner class ArticleVh(private var itemLayoutBinding: ArticleItemLayoutBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {

        fun onBind(article: Article) {
            itemLayoutBinding.apply {
                val context = root.context
                Glide.with(context)
                    .load(article.urlToImage)
                    .centerCrop()
                    .into(imageView)
                title.text = article.title
                author.text = article.author
                postedDate.text = article.publishedAt
                description.text = article.description
            }
        }
    }

    override fun onBindViewHolder(holder: ArticleVh, position: Int) {
        holder.onBind(events[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleVh {
        return ArticleVh(
            ArticleItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.content == newItem.content
        }
    }
}