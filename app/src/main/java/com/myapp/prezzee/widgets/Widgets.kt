package com.myapp.prezzee.widgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.myapp.prezzee.models.movieDetails.MovieDetails
import com.myapp.prezzee.models.movieList.Result
import com.myapp.prezzee.utilities.Constants.IMAGE_URL

@Preview
@Composable
fun MovieDetailsRow(movie: MovieDetails, onItemClick:(Int)->Unit ={ }){

    Card(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth()
        //.height(100.dp)
        .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(modifier = Modifier
                .padding(5.dp)
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp,
            ) {
                Log.e("Image URL",movie.poster_path)
                Image(painter =
                rememberImagePainter(
                    data = IMAGE_URL + movie?.poster_path,
                    builder = {
                        //size(OriginalSize)
                        crossfade(true)
                        //scale(Scale.FIT)
                        transformations(CircleCropTransformation())
                    }
                ),
                    contentDescription = "Picture",
                )

            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title,style = MaterialTheme.typography.h6)
                Text(text = "Overview:  ${movie.overview}", style = MaterialTheme.typography.caption)
            }
        }


    }
}


@Preview
@Composable
fun MovieRow(movie: Result, onItemClick:(Int)->Unit ={ }){

    val retryHash = remember {
        mutableStateOf(0)
    }


    val painter = rememberImagePainter(
        request = ImageRequest.Builder(LocalContext.current)
            .data(movie.poster_path)
            .crossfade(true)
            .transformations(CircleCropTransformation())
            .setParameter("retry_hash", retryHash.value, cacheKey = null)
            .build()
    )

    var expanded by remember {
        mutableStateOf<Boolean>(false)
    }

    Card(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth()
        //.height(100.dp)
        .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(modifier = Modifier
                .padding(5.dp)
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp,
            ) {
                Image(painter =
                rememberImagePainter(
                    data = IMAGE_URL + movie?.poster_path,
                    builder = {
                        //size(OriginalSize)
                        crossfade(true)
                        //scale(Scale.FIT)
                        transformations(CircleCropTransformation())
                    }
                ),
                    contentDescription = "Picture",
                )

            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title,style = MaterialTheme.typography.h6)
                Text(text = " Language: ${movie.original_language}",style = MaterialTheme.typography.caption)
                Text(text = " Title: ${movie.original_title}",style = MaterialTheme.typography.caption)
                Text(text = " Release Date: ${movie.release_date}",style = MaterialTheme.typography.caption)

            }
        }


    }
}