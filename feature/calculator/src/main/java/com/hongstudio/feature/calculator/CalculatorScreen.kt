package com.hongstudio.feature.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.feature.calculator.component.CalculatorBottomButtons
import com.hongstudio.feature.calculator.component.CalculatorLabeledTextField
import com.hongstudio.feature.calculator.model.CalculatorEvent
import com.hongstudio.feature.calculator.model.CalculatorType
import com.hongstudio.feature.calculator.model.CalculatorUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun CalculatorRoute(
    padding: PaddingValues,
    navigateToCalculatorResult: (CalculatorData) -> Unit,
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.event.collectLatest { event ->
                when (event) {
                    is CalculatorEvent.NavigateToCalculatorResult -> {
                        navigateToCalculatorResult(event.calculatorData)
                    }

                    is CalculatorEvent.ShowSnackBar -> {
                        snackBarHostState.showSnackbar(event.message)
                    }
                }
            }
        }
    }

    CalculatorScreen(
        padding = padding,
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        onElectricityChange = viewModel::onElectricityChange,
        onGasChange = viewModel::onGasChange,
        onWaterChange = viewModel::onWaterChange,
        onTrashChange = viewModel::onTrashChange,
        onResetClick = viewModel::onResetClick,
        onResultClick = viewModel::onResultClick
    )
}

@Composable
private fun CalculatorScreen(
    padding: PaddingValues,
    uiState: CalculatorUiState,
    snackBarHostState: SnackbarHostState,
    onElectricityChange: (String) -> Unit,
    onGasChange: (String) -> Unit,
    onWaterChange: (String) -> Unit,
    onTrashChange: (String) -> Unit,
    onResetClick: () -> Unit,
    onResultClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(padding)
            .consumeWindowInsets(padding)
            .imePadding()
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
            isAllInputFilled = uiState.isAllInputFilled,
            onResetClick = onResetClick,
            onResultClick = onResultClick
        )
        SnackbarHost(snackBarHostState)
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorScreenPreview() {
    CalculatorScreen(
        padding = PaddingValues(),
        uiState = CalculatorUiState.DEFAULT,
        snackBarHostState = remember { SnackbarHostState() },
        onElectricityChange = {},
        onGasChange = {},
        onWaterChange = {},
        onTrashChange = {},
        onResetClick = {},
        onResultClick = {}
    )
}