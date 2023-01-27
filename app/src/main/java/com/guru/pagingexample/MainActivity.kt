package com.guru.pagingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guru.pagingexample.databinding.ActivityMainBinding
import com.guru.pagingexample.ui.QuotePagingAdapter
import com.guru.pagingexample.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    lateinit var quotePagingAdapter: QuotePagingAdapter
    private lateinit var quoteViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initRecyclerView()
        initObserver()
    }

    private fun initRecyclerView() {
        quotePagingAdapter = QuotePagingAdapter()
        binding?.recycler?.layoutManager = LinearLayoutManager(this)
        binding?.apply {
            recycler.setHasFixedSize(true)
            recycler.adapter = quotePagingAdapter
        }
    }

    private fun initObserver() {
        binding?.progressCircular?.visibility = View.VISIBLE
        quoteViewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)
        quoteViewModel.list.observe(this, Observer { paginData ->
            quotePagingAdapter.submitData(lifecycle, paginData)
            binding?.progressCircular?.visibility = View.GONE
        })
    }
}
