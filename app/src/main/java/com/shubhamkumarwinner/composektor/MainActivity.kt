package com.shubhamkumarwinner.composektor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.shubhamkumarwinner.composektor.ui.PostViewModel
import com.shubhamkumarwinner.composektor.ui.theme.ComposeKtorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeKtorTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(topBar = {
                        TopAppBar {
                            Text("Ktor Tutorial")
                        }
                    }) {
                        PostData()
                    }
                }
            }
        }
    }
}

@Composable
fun PostData(viewModel: PostViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.posts.isNotEmpty() -> {
                LazyColumn {
                    items(state.posts) { post ->
                        Text(text = post.body)
                    }
                }
            }
            state.error.isNotBlank() -> {
                Text(text = state.error, textAlign = TextAlign.Center)
            }
            state.isLoading -> {
                CircularProgressIndicator()
            }
        }
    }
}