package com.myapp.prezzee.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.myapp.prezzee.data.DataOrException
import com.myapp.prezzee.models.movieDetails.MovieDetails
import com.myapp.prezzee.models.movieList.MovieList
import com.myapp.prezzee.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MoviesRepository):
    ViewModel() {
    val data: MutableState<DataOrException<MovieList, Boolean, Exception>>
            = mutableStateOf(DataOrException(data = null,loading = true,e = Exception("")))
    val dataMovieDetails: MutableState<DataOrException<MovieDetails, Boolean, Exception>>
            = mutableStateOf(DataOrException(data = null,loading = true,e = Exception("")))


    init {
        getMovieList(page = 1)
    }

    fun getMovieList(page: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            data.value.loading = true
            data.value = repository.getMovieList(page)
            if (data.value.toString().isNotEmpty()) {
                data.value.loading = false
            }

        }
    }
    fun getMovieById(id: String){
        viewModelScope.launch (Dispatchers.Main){
            dataMovieDetails.value.loading = true
            dataMovieDetails.value = repository.getMovieByID(id)
            if (data.value.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }
    }
}
