package com.compose.template.utills

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun calculateIndicatorColor(): Color {
    val indicator = MaterialTheme.colorScheme.outline
    val indicatorRed = indicator.red + 0.09f
    val indicatorGreen = indicator.green + 0.1f
    val indicatorBlue = indicator.blue + 0.06f
    return Color(indicatorRed, indicatorGreen, indicatorBlue)
}