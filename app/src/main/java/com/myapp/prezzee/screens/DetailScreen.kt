package com.myapp.prezzee.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.prezzee.viewModel.MovieListViewModel
import com.myapp.prezzee.widgets.MovieDetailsRow

@Composable
fun DetailsScreen(navController: NavController,movieData:String?,viewModel: MovieListViewModel = hiltViewModel()) {



    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Cyan,
            elevation = 15.dp
        ) {
            Row(horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
                ) {
                Spacer(modifier = Modifier.size(10.dp))

                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.size(100.dp))
            }
            Text(text = "Movies")
        }
    }) {
        MainContent(viewModel,movieData)
    }
}
@Composable
fun MainContent(viewModel: MovieListViewModel,movieData:String?){
     viewModel.getMovieById(movieData!!)
    if (viewModel.dataMovieDetails.value.loading == true) {
        CircularProgressIndicator()
    } else {
        val result = viewModel.dataMovieDetails.value.data
        if (result != null) {
            Log.e("Movie", result.imdb_id)
            MovieDetailsRow(movie = result)
        }
    }
}