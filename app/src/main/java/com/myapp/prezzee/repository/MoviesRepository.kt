package com.myapp.prezzee.repository

import com.myapp.prezzee.data.DataOrException
import com.myapp.prezzee.models.movieDetails.MovieDetails
import com.myapp.prezzee.models.movieList.MovieList
import com.myapp.prezzee.network.MovieAPI
import javax.inject.Inject

class MoviesRepository  @Inject constructor(private val api:MovieAPI) {

    private val dataorexceptionMovieList = DataOrException<MovieList,Boolean,Exception>()
    private val dataorexceptionMovieDetails = DataOrException<MovieDetails,Boolean,Exception>()

    suspend fun getMovieList(page: Int):DataOrException<MovieList,Boolean,Exception>{
        try {
            dataorexceptionMovieList.loading = true
            dataorexceptionMovieList.e = null
            dataorexceptionMovieList.data = api.getMovieList(page)
            if(dataorexceptionMovieList.data.toString().isNotEmpty())
                dataorexceptionMovieList.loading = false

        }catch(ex: Exception){
            dataorexceptionMovieList.loading = false
            dataorexceptionMovieList.e = ex
            dataorexceptionMovieList.data = null
        }
        return dataorexceptionMovieList
    }
    suspend fun getMovieByID(id:String):DataOrException<MovieDetails,Boolean,Exception>{
        try {
            dataorexceptionMovieDetails.loading = true
            dataorexceptionMovieDetails.e = null
            dataorexceptionMovieDetails.data = api.getMovieByID(id)
            if(dataorexceptionMovieDetails.data.toString().isNotEmpty())
                dataorexceptionMovieDetails.loading = false
        }catch(ex: Exception){
            dataorexceptionMovieDetails.loading = false
            dataorexceptionMovieDetails.e = ex
            dataorexceptionMovieDetails.data = null
        }
        return dataorexceptionMovieDetails
    }
}