package com.myapp.prezzee.models.movieList

data class MovieList(
    val page: Int = 0,
    val results: List<Result> = ArrayList(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)