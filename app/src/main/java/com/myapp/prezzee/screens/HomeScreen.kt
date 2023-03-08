package com.myapp.prezzee.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.prezzee.navigations.MovieScreens
import com.myapp.prezzee.viewModel.MovieListViewModel
import com.myapp.prezzee.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController,viewModel: MovieListViewModel = hiltViewModel()){
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Cyan,
            elevation = 15.dp) {
            Text(text = "Movies")
        }
    }) {
            MainContent(navController = navController,viewModel)
    }
}
@Composable
fun MainContent(navController: NavController,viewModel: MovieListViewModel){

        if (viewModel.data.value.loading == true) {
            CircularProgressIndicator()
        } else {
            val movielist = viewModel.data.value.data?.results?.toMutableList()
            Column(modifier = Modifier.padding(12.dp)) {
                LazyColumn() {
                    movielist?.let { it ->
                        items(items = it) {
                            MovieRow(movie = it) { movie ->
                                navController.navigate(route = MovieScreens.DetailsScreen.name + "/${movie}")
                                Log.d("Movie CLick", "items.")
                            }
                        }
                    }
                }
            }
        }

}