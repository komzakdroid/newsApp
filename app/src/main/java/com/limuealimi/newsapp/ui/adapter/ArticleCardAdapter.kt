package com.limuealimi.newsapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.limuealimi.newsapp.databinding.ArticleItemLayoutBinding
import com.limuealimi.newsapp.domain.model.Article

class ArticleCardAdapter : RecyclerView.Adapter<ArticleCardAdapter.GetCardViewHolder>() {

    private var events = ArrayList<Article>()

    fun updateEvents(newViewItems: List<Article>) {
        val diffCallback = EventDiffUtilsCallBack(events, newViewItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        events.addAll(newViewItems)
       diffResult.dispatchUpdatesTo(this)/*
        events = newViewItems as ArrayList<Article>
        notifyDataSetChanged()*/
    }

    private var listener: ItemOnClickListener? = null

    fun onClickListener(listener: ItemOnClickListener) {
        this.listener = listener
    }

    inner class GetCardViewHolder
    constructor(
        private val binding: ArticleItemLayoutBinding,
        private val listener: ItemOnClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Article) {
            with(binding) {
                val context = binding.root.context
                Glide.with(context)
                    .load(data.urlToImage)
                    .centerCrop()
                    .into(imageView)
                title.text = data.title
                author.text = data.author
                postedDate.text = data.publishedAt
                description.text = data.description
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GetCardViewHolder(
            ArticleItemLayoutBinding.inflate(LayoutInflater.from(parent.context)),
            listener
        )

    override fun onBindViewHolder(holder: GetCardViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount() = events.size

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