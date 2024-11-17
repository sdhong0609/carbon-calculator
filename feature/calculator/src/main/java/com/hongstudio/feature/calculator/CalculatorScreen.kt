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
import com.hongstudio.core.model.CalculatorSelected
import com.hongstudio.feature.calculator.component.CalculatorBottomButtons
import com.hongstudio.feature.calculator.component.CalculatorLabeledTextField
import com.hongstudio.feature.calculator.model.CalculatorType
import com.hongstudio.feature.calculator.model.CalculatorUiState

@Composable
internal fun CalculatorRoute(
    padding: PaddingValues,
    calculatorSelected: CalculatorSelected,
    navigateToCalculatorResult: (CalculatorData) -> Unit,
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CalculatorScreen(
        padding = padding,
        uiState = uiState,
        calculatorSelected = calculatorSelected,
        onElectricityChange = viewModel::onElectricityChange,
        onGasChange = viewModel::onGasChange,
        onWaterChange = viewModel::onWaterChange,
        onTrashChange = viewModel::onTrashChange,
        onResetClick = viewModel::onResetClick,
        calculatorData = viewModel::calculatorData,
        navigateToCalculatorResult = navigateToCalculatorResult
    )
}

@Composable
private fun CalculatorScreen(
    padding: PaddingValues,
    uiState: CalculatorUiState,
    calculatorSelected: CalculatorSelected,
    onElectricityChange: (String) -> Unit,
    onGasChange: (String) -> Unit,
    onWaterChange: (String) -> Unit,
    onTrashChange: (String) -> Unit,
    onResetClick: () -> Unit,
    calculatorData: () -> CalculatorData,
    navigateToCalculatorResult: (CalculatorData) -> Unit,
) {
    val scrollState = rememberScrollState()

    val (isElectricitySelected, isGasSelected, isWaterSelected, isTrashSelected) = calculatorSelected

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isElectricitySelected) {
            CalculatorLabeledTextField(
                calculatorType = CalculatorType.ELECTRICITY,
                input = uiState.electricityInput,
                onValueChange = onElectricityChange
            )
        }
        if (isGasSelected) {
            CalculatorLabeledTextField(
                calculatorType = CalculatorType.GAS,
                input = uiState.gasInput,
                onValueChange = onGasChange
            )
        }
        if (isWaterSelected) {
            CalculatorLabeledTextField(
                calculatorType = CalculatorType.WATER,
                input = uiState.waterInput,
                onValueChange = onWaterChange
            )
        }
        if (isTrashSelected) {
            CalculatorLabeledTextField(
                calculatorType = CalculatorType.TRASH,
                input = uiState.trashInput,
                onValueChange = onTrashChange
            )
        }
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
        calculatorSelected = CalculatorSelected(true, true, true, true),
        onElectricityChange = {},
        onGasChange = {},
        onWaterChange = {},
        onTrashChange = {},
        onResetClick = {},
        calculatorData = { CalculatorData.create(0.0, 0.0, 0.0, 0.0) }
    )
}