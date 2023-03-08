package com.myapp.prezzee.di

import com.myapp.prezzee.network.MovieAPI
import com.myapp.prezzee.repository.MoviesRepository
import com.myapp.prezzee.utilities.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMovieRepository(api:MovieAPI) = MoviesRepository(api)

    @Singleton
    @Provides
    fun provideQuestionAPI():MovieAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MovieAPI :: class.java)
    }

}