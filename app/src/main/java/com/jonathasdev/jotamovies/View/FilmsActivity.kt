package com.jonathasdev.jotamovies.View

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.jonathasdev.jotamovies.API.MovieAPI
import com.jonathasdev.jotamovies.adapter.MovieAdapter
import com.jonathasdev.jotamovies.databinding.ActivityFilmsBinding
import com.jonathasdev.jotamovies.model.MoviesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class FilmsActivity : AppCompatActivity() {

    private val movieAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieAPI::class.java)
    }
    private val binding by lazy {
        ActivityFilmsBinding.inflate(layoutInflater)
    }

    private val TAG = "movies_info"
    private var movieAdapter : MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        movieAdapter = MovieAdapter()
        binding.rvFilmes.adapter = movieAdapter
        binding.rvFilmes.layoutManager = GridLayoutManager(this, 2)

    }

    //Carregar dados
    override fun onStart() {
        super.onStart()
        getMovies()
    }

    private fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {

            var response : retrofit2.Response<MoviesResponse>? = null

            try {
                response = movieAPI.getMovies()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if ( response != null )
                if (response.isSuccessful) {

                    val moviesResponse = response.body()
                    val moviesList = moviesResponse?.results

                    moviesList?.forEach { filme ->
                        Log.i(TAG, "titulo: ${filme.title}")
                    }

                    withContext( Dispatchers.Main ) {
                        movieAdapter?.addMovieList(moviesList)
                    }

                }else{
                    Log.i(TAG, "erro: ${response.code()}" )
                }



        }
    }
}