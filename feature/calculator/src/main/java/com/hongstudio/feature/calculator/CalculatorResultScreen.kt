package com.hongstudio.feature.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hongstudio.feature.calculator.model.CalculatorResultUiState

@Composable
internal fun CalculatorResultRoute(
    padding: PaddingValues,
    viewModel: CalculatorResultViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CalculatorResultScreen(
        padding = padding,
        uiState = uiState
    )
}

@Composable
private fun CalculatorResultScreen(
    padding: PaddingValues,
    uiState: CalculatorResultUiState
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("총 CO₂ 발생량")
        Text("${uiState.total} kg/월")
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorResultScreenPreview() {
    CalculatorResultScreen(
        padding = PaddingValues(),
        uiState = CalculatorResultUiState()
    )
}