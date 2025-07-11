package com.example.newsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapplication.ui.theme.NewsApplicationTheme
import com.example.newsapplication.view.components.BouncingDotsLoader
import com.example.newsapplication.view.screen.HomeApp
import com.example.newsapplication.view.screen.SplashScreen
import com.example.newsapplication.viewmodel.NewsViewModel
import com.example.newsapplication.viewmodel.Result
import com.example.newsapplication.viewmodel.SplashScreenViewModel

class MainActivity : ComponentActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private val splashViewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NewsApplicationTheme {
                val showSplash by splashViewModel.isSplashVisible.collectAsState()
                val newsState by newsViewModel.newsState.collectAsState()

                if (showSplash) {
                    SplashScreen()
                } else {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        ) {
                            when(newsState) {
                                is Result.Loading -> {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        BouncingDotsLoader()
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Text(text = "Loading news...", fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
                                    }
                                }

                                is Result.Success -> {
                                    HomeApp(viewModel = newsViewModel)
                                }

                                is Result.Error -> {
                                    val message = (newsState as Result.Error).message ?: "Something went wrong"
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(text = "🚫", fontSize = 48.sp)
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = message,
                                            color = MaterialTheme.colorScheme.error,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Button(onClick = { newsViewModel.retry() }) {
                                            Text("Retry")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}