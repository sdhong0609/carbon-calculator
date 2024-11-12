package com.hongstudio.feature.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hongstudio.feature.calculator.component.CalculatorLabeledTextField

@Composable
internal fun CalculatorRoute(padding: PaddingValues) {
    CalculatorScreen(padding = padding)
}

@Composable
private fun CalculatorScreen(padding: PaddingValues) {
    val scrollState = rememberScrollState()
    var electricityInput by rememberSaveable { mutableStateOf("") }
    var gasInput by rememberSaveable { mutableStateOf("") }
    var waterInput by rememberSaveable { mutableStateOf("") }
    var trashInput by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
            .padding(all = 16.dp)
    ) {
        CalculatorLabeledTextField("전기 사용량", electricityInput) { electricityInput = it }
        CalculatorLabeledTextField("가스 사용량", gasInput) { gasInput = it }
        CalculatorLabeledTextField("수도 사용량", waterInput) { waterInput = it }
        CalculatorLabeledTextField("폐기물 사용량", trashInput) { trashInput = it }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorScreenPreview() {
    CalculatorScreen(padding = PaddingValues())
}