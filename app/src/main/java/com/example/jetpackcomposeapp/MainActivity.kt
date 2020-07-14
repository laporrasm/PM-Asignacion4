package com.example.jetpackcomposeapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.*
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.gravity
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.material.*
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.jetpackcomposeapp.ui.JetpackComposeAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                LoginScreen()
            }
        }
    }

    @Composable
    fun LoginScreen() {
        val input = state { TextFieldValue("") }
        Text(
                text = "Cine Luna App",
                modifier = Modifier.gravity(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h4
        )
        Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
        ) {
            Text(text = "Your name:")
            FilledTextField(
                    value = input.value,
                    onValueChange = { input.value = it },
                    label = { Text(text = "Enter your name here") },
                    modifier = Modifier.fillMaxWidth())
        }
        Button(
                onClick = {
                    val intent = Intent(this, MoviesListActivity::class.java).apply {
                        putExtra("name", input.value.text)
                    }
                    startActivity(intent)
                },
                modifier = Modifier.gravity(Alignment.CenterHorizontally).fillMaxWidth()
        ) {
            Text(text = "Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
    }
}

@Composable
fun MyApp(content: @Composable() () -> Unit) {
    JetpackComposeAppTheme {
        Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(16.dp)
        ) {
            content()
        }
    }
}

@Composable
fun movieIcon(modifier: Modifier) {
    Icon(
        asset = vectorResource(id = R.drawable.ic_baseline_local_movies_24),
        modifier = modifier
    )
}