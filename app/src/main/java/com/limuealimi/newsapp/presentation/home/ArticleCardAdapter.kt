package com.limuealimi.newsapp.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.limuealimi.newsapp.R
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.databinding.ArticleItemLayoutBinding

class ArticleCardAdapter(context: Context) :
    PagingDataAdapter<Article, ArticleCardAdapter.ArticleViewHolder>(ArticleDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            layoutInflater.inflate(R.layout.article_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    class ArticleViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val viewBinding by viewBinding(ArticleItemLayoutBinding::bind)

        fun onBind(article: Article?) {
            with(viewBinding) {
                imageView.load(article?.urlToImage)
                title.text = article?.title
                author.text = article?.author
                postedDate.text = article?.publishedAt
                description.text = article?.description
            }

        }
    }

    private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title && oldItem.url == newItem.url
        }
    }
}