package com.guru.pagingexample.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.guru.pagingexample.network.QuoteAPI
import com.guru.pagingexample.paging.QuotePagingSource
import javax.inject.Inject

class QuoteRepository @Inject constructor(val quoteAPI:QuoteAPI){

    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {QuotePagingSource(quoteAPI)}
    ).liveData
}
