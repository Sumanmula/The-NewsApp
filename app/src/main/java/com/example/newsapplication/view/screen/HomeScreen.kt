package com.example.newsapplication.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapplication.view.components.NewsCard
import com.example.newsapplication.viewmodel.NewsViewModel
import com.example.newsapplication.viewmodel.Result

@Composable
fun HomeApp(viewModel: NewsViewModel) {

    val newsState by viewModel.newsState.collectAsState()

    val articles = when (newsState) {
        is Result.Success -> (newsState as Result.Success).data?.articles ?: emptyList()
        else -> emptyList()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(articles) { article ->
            NewsCard(article)
        }
    }
}