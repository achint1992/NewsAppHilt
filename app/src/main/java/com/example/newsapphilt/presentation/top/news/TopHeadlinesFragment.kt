package com.example.newsapphilt.presentation.top.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.newsapphilt.MainActivity
import com.example.newsapphilt.R
import com.example.newsapphilt.data.util.Resource
import com.example.newsapphilt.databinding.FragmentTopHeadlinesBinding
import com.example.newsapphilt.presentation.adapter.NewsAdapter
import com.example.newsapphilt.presentation.news.NewsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TopHeadlinesFragment : Fragment() {

    lateinit var binding: FragmentTopHeadlinesBinding
    lateinit var newsViewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    var page: Int = 0
    var isLoading = false
    var isScrollin = false
    var isLast = false
    var totalPages = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopHeadlinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        newsViewModel = (activity as MainActivity).viewModel
        setUpList()
        showList()
        setSearchView()
    }

    fun setUpList() {
        newsAdapter = NewsAdapter()
        binding.rvNews.apply {
            this.adapter = newsAdapter
            this.addOnScrollListener(onScroll)
            (this.adapter as NewsAdapter).listner = {
                val bundle = Bundle().apply {
                    putSerializable("article", it)
                }
                findNavController().navigate(
                    R.id.action_topHeadlinesFragment_to_viewNewsFragment,
                    bundle
                )
            }
            this.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }

    fun showList() {
        newsViewModel.getNewsFromRemote(page)
        newsViewModel.remoteNewsHeadLines.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {

                    hideProgressBar()
                    newsAdapter.differ.submitList(it.data?.articles)
                    val totalResult: Int = it.data?.totalResults!!
                    if (totalResult % 20 == 0) {
                        totalPages = totalResult / 20
                    } else {
                        totalPages = (totalResult / 20) + 1
                    }
                    isLast = page == totalPages
                }
                is Resource.Error -> {
                    Toast.makeText(activity, "Found Error = ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }

            }
        }
    }

    fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.e("Query is", query + " ")
                page = 1
                query?.let { newsViewModel.searchNewsFromRemote(it, page) }
                searchList()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        binding.searchView.setOnCloseListener {
            setUpList()
            showList()
            false
        }
    }

    fun searchList() {
        newsViewModel.searchHeadlines.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {

                    hideProgressBar()
                    Log.e("Data is", it.data?.articles.toString())
                    newsAdapter.differ.submitList(it.data?.articles)
                    val totalResult: Int = it.data?.totalResults!!
                    if (totalResult % 20 == 0) {
                        totalPages = totalResult / 20
                    } else {
                        totalPages = (totalResult / 20) + 1
                    }
                    isLast = page == totalPages
                }
                is Resource.Error -> {
                    Toast.makeText(activity, "Found Error = ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }

            }
        }
    }


    fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
    }

    var onScroll: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrollin = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            var layoutManager = recyclerView.layoutManager as LinearLayoutManager
            var size = layoutManager.itemCount
            var visibleItem = layoutManager.childCount
            var topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedEnd = topPosition + visibleItem >= size
            val shouldPaginate = hasReachedEnd && !isLast && !isLoading && isScrollin
            if (shouldPaginate) {
                page++
                newsViewModel.getNewsFromRemote(page)
                isScrollin = false
            }

        }
    }

}