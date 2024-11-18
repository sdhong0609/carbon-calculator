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
        onElectricityChange = viewModel::onElectricityChange,
        onGasChange = viewModel::onGasChange,
        onWaterChange = viewModel::onWaterChange,
        onTrashChange = viewModel::onTrashChange,
        onResetClick = viewModel::onResetClick,
        calculatorData = viewModel::createCalculatorData,
        navigateToCalculatorResult = navigateToCalculatorResult
    )
}

@Composable
private fun CalculatorScreen(
    padding: PaddingValues,
    uiState: CalculatorUiState,
    onElectricityChange: (String) -> Unit,
    onGasChange: (String) -> Unit,
    onWaterChange: (String) -> Unit,
    onTrashChange: (String) -> Unit,
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
        if (uiState.isElectricityVisible) {
            CalculatorLabeledTextField(
                calculatorType = CalculatorType.ELECTRICITY,
                input = uiState.electricityInput,
                onValueChange = onElectricityChange
            )
        }
        if (uiState.isGasVisible) {
            CalculatorLabeledTextField(
                calculatorType = CalculatorType.GAS,
                input = uiState.gasInput,
                onValueChange = onGasChange
            )
        }
        if (uiState.isWaterVisible) {
            CalculatorLabeledTextField(
                calculatorType = CalculatorType.WATER,
                input = uiState.waterInput,
                onValueChange = onWaterChange
            )
        }
        if (uiState.isTrashVisible) {
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
        onElectricityChange = {},
        onGasChange = {},
        onWaterChange = {},
        onTrashChange = {},
        onResetClick = {},
        calculatorData = { CalculatorData(0.0, 0.0, 0.0, 0.0) }
    )
}