package com.hongstudio.feature.calculator

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.feature.calculator.component.CalculatorBottomButtons
import com.hongstudio.feature.calculator.component.CalculatorLabeledTextField
import com.hongstudio.feature.calculator.model.CalculatorType

@Composable
internal fun CalculatorRoute(padding: PaddingValues, onResultClick: (CalculatorData) -> Unit) {
    CalculatorScreen(padding = padding, onResultClick = onResultClick)
}

@Composable
private fun CalculatorScreen(padding: PaddingValues, onResultClick: (CalculatorData) -> Unit) {
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
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalculatorLabeledTextField(
            calculatorType = CalculatorType.ELECTRICITY,
            input = electricityInput
        ) { electricityInput = it }
        CalculatorLabeledTextField(
            calculatorType = CalculatorType.GAS,
            input = gasInput
        ) { gasInput = it }
        CalculatorLabeledTextField(
            calculatorType = CalculatorType.WATER,
            input = waterInput
        ) { waterInput = it }
        CalculatorLabeledTextField(
            calculatorType = CalculatorType.TRASH,
            input = trashInput
        ) { trashInput = it }
        CalculatorBottomButtons(
            onResetClick = {
                electricityInput = ""
                gasInput = ""
                waterInput = ""
                trashInput = ""
            },
            onResultClick = {
                val electricity = electricityInput.ifEmpty { "0" }.toDouble()
                val gas = gasInput.ifEmpty { "0" }.toDouble()
                val water = waterInput.ifEmpty { "0" }.toDouble()
                val trash = trashInput.ifEmpty { "0" }.toDouble()

                onResultClick(
                    CalculatorData(
                        electricity = electricity,
                        gas = gas,
                        water = water,
                        trash = trash
                    )
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorScreenPreview() {
    CalculatorScreen(padding = PaddingValues(), onResultClick = {})
}