package com.myapp.prezzee.network

import com.myapp.prezzee.models.movieDetails.MovieDetails
import com.myapp.prezzee.models.movieList.MovieList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MovieAPI
{
    @GET("popular?api_key=569b654c07c67068bde7b48c2840a27a"+"&"+"page=")
    suspend fun getMovieList(@Query("page") page: Int): MovieList

    @GET("{id}?api_key=569b654c07c67068bde7b48c2840a27a")
    suspend fun getMovieByID(@Path("id") id:String): MovieDetails
}