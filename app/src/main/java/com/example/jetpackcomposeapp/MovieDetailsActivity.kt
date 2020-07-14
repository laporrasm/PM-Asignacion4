package com.example.jetpackcomposeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.ColumnScope.gravity
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.jetpackcomposeapp.ui.JetpackComposeAppTheme

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movie = intent.getSerializableExtra("movie")

        setContent {
            MyApp {
                movieDetailsView(movie = movie as Movie)
            }
        }
    }

    @Composable
    fun movieDetailsView(movie : Movie) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalGravity = Alignment.CenterHorizontally
        ) {
            Text(text = movie.name)
            movieIcon(modifier = Modifier.padding(vertical = 20.dp))
            Text(text = movie.review)
        }

        Button(
            onClick = {
                finish()
            },
            modifier = Modifier.gravity(Alignment.CenterHorizontally).fillMaxWidth()
        ) {
            Text(text = "Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    MyApp {
        
    }
}