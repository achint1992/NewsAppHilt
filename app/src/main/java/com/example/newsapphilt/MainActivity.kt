package com.example.newsapphilt

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapphilt.databinding.ActivityMainBinding
import com.example.newsapphilt.presentation.news.NewsViewModel
import com.example.newsapphilt.presentation.news.NewsViewModelFactory
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: NewsViewModelFactory
    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        val fragmentController = navFragment?.findNavController()
        fragmentController?.let { binding.bottomNav.setupWithNavController(it) }
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        Log.e("trying new Branch ","Development Branch")
        Log.e("Changing","Commit 2")
    }


}