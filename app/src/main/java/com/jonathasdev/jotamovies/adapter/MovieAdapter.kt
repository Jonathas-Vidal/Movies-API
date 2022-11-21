package com.jonathasdev.jotamovies.adapter

import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.jonathasdev.jotamovies.View.FilmsActivity
import com.jonathasdev.jotamovies.databinding.RvItemMovieBinding
import com.jonathasdev.jotamovies.model.Movies
import com.squareup.picasso.Picasso


class MovieAdapter : Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList: List<Movies>? = null

    fun addMovieList(lista: List<Movies>?) {
        movieList = lista
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: RvItemMovieBinding) :
        RecyclerView.ViewHolder(itemView.root) {
            private val binding : RvItemMovieBinding
            init {
                binding = itemView
            }
            fun bind(movie : Movies) {

                binding.tvTitulo.text = movie.title

                val baseImgUrl = "https://image.tdmb.org/t/p/"
                val imgSize = "w780"
                val imgName = movie.backdrop_path
                val link = baseImgUrl + imgSize + imgSize

                Picasso.get()
                    .load(link)
                    .into(binding.RvLoader)
            }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from( parent.context )
        val itemMovieBinding = RvItemMovieBinding.inflate(
            layoutInflater,parent,false)

        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList?.get(position)
        holder.bind(movie!!)
    }

    override fun getItemCount(): Int {
        if (movieList != null) {
            return movieList!!.size
        }
        return 0
    }
}