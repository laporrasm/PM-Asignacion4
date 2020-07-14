package com.example.jetpackcomposeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Card
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class MoviesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name")

        MoviesManager().getMovies {
            setContent {
                MyApp {
                    moviesView(it, name = name!!)
                }
            }
        }
    }

    @Composable
    fun moviesView(moviesList : ArrayList<Movie>, name : String) {
        Text(text = "Hello, $name!", modifier = Modifier.padding(vertical = 20.dp))

        Column(modifier = Modifier.weight(1f)) {
            for (movie in moviesList) {
                MovieItem(movie = movie, action = {
                    val intent = Intent(this@MoviesListActivity, MovieDetailsActivity::class.java).apply {
                        putExtra("movie", movie)
                    }
                    startActivity(intent)
                })
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, action : (Movie) -> Unit) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding (8.dp)
            .clickable (onClick = {
                action (movie)
            })
    ) {
        MovieItemContent(movie = movie)
    }
}

@Composable
fun MovieItemContent(movie : Movie) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        verticalGravity = Alignment.CenterVertically
    ) {
        movieIcon(modifier = Modifier)
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(
                text = movie.name,
                style = MaterialTheme.typography.h5
            )
            Text(text = movie.time)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyApp {
        /*moviesView(MoviesManager().getMovies() as ArrayList<Movie>, name = "")*/
    }
}