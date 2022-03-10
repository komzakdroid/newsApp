package com.limuealimi.newsapp.ui.pages

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.limuealimi.newsapp.R
import com.limuealimi.newsapp.databinding.FragmentHomeBinding
import com.limuealimi.newsapp.databinding.FragmentNewsBinding
import com.limuealimi.newsapp.domain.model.Article
import com.limuealimi.newsapp.ui.MainViewModel
import com.limuealimi.newsapp.ui.adapter.ArticleCardAdapter
import com.limuealimi.newsapp.utils.State
import com.limuealimi.newsapp.utils.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: MainViewModel by viewModel()

    private lateinit var cardAdapter: ArticleCardAdapter
    private val dataList = ArrayList<Article>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()

        initData()

        onClickItems()
    }

    private fun initData() {
        viewModel.getArticlesData()
        observeArticleData()
    }

    @SuppressLint("NewApi")
    private fun observeArticleData() {
        binding.apply {
            viewModel.articleData.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is State.Loading -> {
                        progressBar.isVisible(true)
                        llEmpty.isVisible(false)
                        println("Loading->")
                    }
                    is State.Error -> {
                        progressBar.isVisible(false)
                        llEmpty.isVisible(false)
                        println("Error state -> ${state.error}")
                    }
                    is State.NoConnection -> {
                        progressBar.isVisible(false)
                    }
                    is State.Content -> {
                        progressBar.isVisible(false)
                        llEmpty.isVisible(false)
                        dataList.clear()
                        dataList.addAll(state.content)
                        cardAdapter.updateEvents(dataList)
                        rv.isVisible(true)
                    }
                    is State.Empty -> {
                        progressBar.isVisible(false)
                        llEmpty.isVisible(true)
                        rv.isVisible(false)
                        print("Empty state")
                    }
                }
            }
        }
    }

    private fun onClickItems() {
        cardAdapter.onClickListener(object : ArticleCardAdapter.ItemOnClickListener {
            override fun onItemClickOption(position: Int, data: Article, itemView: View) {
                findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
            }

        })
    }

    private fun initRv() {
        cardAdapter = ArticleCardAdapter()
        binding?.rv?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = cardAdapter
        }
    }

}