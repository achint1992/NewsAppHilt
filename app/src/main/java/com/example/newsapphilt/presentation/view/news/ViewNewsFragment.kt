package com.example.newsapphilt.presentation.view.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.newsapphilt.R
import com.example.newsapphilt.databinding.FragmentViewNewsBinding


class ViewNewsFragment : Fragment() {

    lateinit var binding: FragmentViewNewsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentViewNewsBinding.bind(view)
        val args: ViewNewsFragmentArgs by navArgs()
        val article = args.article

        binding.wvInfo.apply {
            webViewClient= WebViewClient()
            loadUrl(article.url)
        }
    }


}