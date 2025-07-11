package com.example.newsapplication.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapplication.viewmodel.BouncingDotViewModel
import com.example.newsapplication.R

@Composable
fun BouncingDotsLoader(
    viewModel: BouncingDotViewModel = viewModel()
) {
    val offsets = viewModel.dotOffsets.collectAsState().value

    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.news_app_project_logo),
            contentDescription = "News App Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            offsets.forEachIndexed { index, offset ->
                Box(
                    modifier = Modifier
                        .offset(y = offset.dp)
                        .size(12.dp)
                        .background(
                            when (index) {
                                0 -> Color.Blue
                                1 -> Color.Red
                                else -> Color.Green
                            },
                            shape = CircleShape
                        )
                )
            }
        }
    }
}