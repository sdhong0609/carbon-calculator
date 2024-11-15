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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.feature.calculator.component.CalculatorBottomButtons
import com.hongstudio.feature.calculator.component.CalculatorLabeledTextField
import com.hongstudio.feature.calculator.model.CalculatorType
import com.hongstudio.feature.calculator.model.CalculatorUiState

@Composable
internal fun CalculatorRoute(
    padding: PaddingValues,
    navigateToCalculatorResult: (CalculatorData) -> Unit,
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CalculatorScreen(
        padding = padding,
        uiState = uiState,
        onInputElectricity = viewModel::onInputElectricity,
        onInputGas = viewModel::onInputGas,
        onInputWater = viewModel::onInputWater,
        onInputTrash = viewModel::onInputTrash,
        onResetClick = viewModel::onResetClick,
        calculatorData = viewModel::calculatorData,
        navigateToCalculatorResult = navigateToCalculatorResult
    )
}

@Composable
private fun CalculatorScreen(
    padding: PaddingValues,
    uiState: CalculatorUiState,
    onInputElectricity: (String) -> Unit,
    onInputGas: (String) -> Unit,
    onInputWater: (String) -> Unit,
    onInputTrash: (String) -> Unit,
    onResetClick: () -> Unit,
    calculatorData: () -> CalculatorData,
    navigateToCalculatorResult: (CalculatorData) -> Unit,
) {
    val scrollState = rememberScrollState()

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
            input = uiState.electricityInput,
            onInputChange = onInputElectricity
        )
        CalculatorLabeledTextField(
            calculatorType = CalculatorType.GAS,
            input = uiState.gasInput,
            onInputChange = onInputGas
        )
        CalculatorLabeledTextField(
            calculatorType = CalculatorType.WATER,
            input = uiState.waterInput,
            onInputChange = onInputWater
        )
        CalculatorLabeledTextField(
            calculatorType = CalculatorType.TRASH,
            input = uiState.trashInput,
            onInputChange = onInputTrash
        )
        CalculatorBottomButtons(
            onResetClick = onResetClick,
            onResultClick = {
                navigateToCalculatorResult(calculatorData())
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorScreenPreview() {
    CalculatorScreen(
        padding = PaddingValues(),
        navigateToCalculatorResult = {},
        uiState = CalculatorUiState(),
        onInputElectricity = {},
        onInputGas = {},
        onInputWater = {},
        onInputTrash = {},
        onResetClick = {},
        calculatorData = { CalculatorData.create(0.0, 0.0, 0.0, 0.0) }
    )
}