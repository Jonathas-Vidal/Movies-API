package com.jonathasdev.jotamovies.API

import com.jonathasdev.jotamovies.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.Response

interface MovieAPI {

    //Trabalho executado por uma função de Apoio
    //A BaseURL é a principal, essa interface é a secundária (Resto do link)

    //Rota / EndPoint (Parte alterável da URL)
    @GET("movie/popular?api_key=c84dc73536e6d4847bfd3e07c0c3499e")
    suspend fun getMovies() : Response<MoviesResponse>

}