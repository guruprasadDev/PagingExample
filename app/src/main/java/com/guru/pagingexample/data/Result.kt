package com.guru.pagingexample.data



data class Result(
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val id: String,
    val length: Int,
    val tags: List<String>
)