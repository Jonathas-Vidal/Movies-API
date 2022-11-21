package com.jonathasdev.jotamovies.model

data class MoviesResponse(
    val page: Int,
    val results: List<Movies>,
    val total_pages: Int,
    val total_results: Int
)