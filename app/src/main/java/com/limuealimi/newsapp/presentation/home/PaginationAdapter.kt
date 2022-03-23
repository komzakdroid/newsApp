package com.limuealimi.newsapp.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.limuealimi.newsapp.data.model.Article
import com.limuealimi.newsapp.databinding.ArticleItemLayoutBinding
import com.limuealimi.newsapp.databinding.ItemProgressBinding

class PaginationAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var isLoadingAdded = false
    private var LOADING = 0
    private var DATA = 1
    private var events = ArrayList<Article>()

    fun addAll(otherList: List<Article>) {
        events.addAll(otherList)
    }

    fun addLoading() {
        isLoadingAdded = true
    }

    fun updateEvents(newViewItems: List<Article>) {
        val diffCallback = EventDiffUtilsCallBack(events, newViewItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        events.addAll(newViewItems)
        diffResult.dispatchUpdatesTo(this)
    }

    private var listener: ItemOnClickListener? = null

    fun onClickListener(listener: ItemOnClickListener) {
        this.listener = listener
    }

    inner class ArticleVh(var itemLayoutBinding: ArticleItemLayoutBinding) :
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

    inner class ProgressVh(var itemProgressBinding: ItemProgressBinding) :
        RecyclerView.ViewHolder(itemProgressBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LOADING) {
            return ProgressVh(
                ItemProgressBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return ArticleVh(
                ArticleItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA) {
            val articleVh = holder as ArticleVh
            articleVh.onBind(events[position])
        } else {
            val progressVh = holder as ProgressVh
            progressVh.itemProgressBinding.progress.isVisible = true
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == events.size - 1 && isLoadingAdded) {
            return LOADING
        }
        return DATA
    }

    interface ItemOnClickListener {
        fun onItemClickOption(position: Int, data: Article, itemView: View)
    }
}

class EventDiffUtilsCallBack(
    private val oldViewItems: List<Article>,
    private val newViewItems: List<Article>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldViewItems.size
    override fun getNewListSize() = newViewItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldViewItems[oldItemPosition] == newViewItems[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldViewItems[oldItemPosition] == newViewItems[newItemPosition]
}