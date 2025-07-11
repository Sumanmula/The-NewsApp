package com.example.newsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BouncingDotViewModel : ViewModel() {

    private val _dotOffsets = MutableStateFlow(listOf(0f, 0f, 0f))
    val dotOffsets = _dotOffsets.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                val values = listOf(
                    animateDot(delay = 0),
                    animateDot(delay = 100),
                    animateDot(delay = 200)
                )
                _dotOffsets.emit(values)
                kotlinx.coroutines.delay(300)
            }
        }
    }

    private suspend fun animateDot(delay: Int): Float {
        return listOf(0f, -10f, 0f).random()
    }
}
