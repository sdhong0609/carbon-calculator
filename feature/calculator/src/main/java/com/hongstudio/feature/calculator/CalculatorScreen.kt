package com.hongstudio.feature.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun CalculatorRoute(padding: PaddingValues) {
    CalculatorScreen(padding = padding)
}

@Composable
private fun CalculatorScreen(padding: PaddingValues) {
    Column(
        modifier = Modifier.padding(padding)
    ) {
        Text("Calculator Screen")
    }
}

@Preview
@Composable
private fun CalculatorScreenPreview() {
    CalculatorScreen(padding = PaddingValues())
}