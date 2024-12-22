package com.hongstudio.feature.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.hongstudio.core.model.CalculatorInputData
import com.hongstudio.core.model.CalculatorType
import com.hongstudio.feature.calculator.component.CalculatorLabeledTextField
import com.hongstudio.feature.calculator.component.DoubleButtonScreen
import com.hongstudio.feature.calculator.model.CalculatorEvent
import com.hongstudio.feature.calculator.model.CalculatorUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun CalculatorRoute(
    padding: PaddingValues,
    navigateToCalculatorResult: (List<CalculatorInputData>) -> Unit,
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.event.collectLatest { event ->
                when (event) {
                    is CalculatorEvent.NavigateToCalculatorResult -> {
                        navigateToCalculatorResult(event.inputCompletedCalculators)
                    }

                    is CalculatorEvent.ShowSnackBar -> {
                        snackBarHostState.showSnackbar(context.getString(R.string.please_fill_all_input))
                    }
                }
            }
        }
    }

    CalculatorScreen(
        padding = padding,
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        onInputChange = viewModel::onInputChange,
        onResetClick = viewModel::onResetClick,
        onResultClick = viewModel::onResultClick
    )
}

@Composable
private fun CalculatorScreen(
    padding: PaddingValues,
    uiState: CalculatorUiState,
    snackBarHostState: SnackbarHostState,
    onInputChange: (CalculatorType, String) -> Unit,
    onResetClick: () -> Unit,
    onResultClick: () -> Unit
) {
    DoubleButtonScreen(
        padding = padding,
        onLeftButtonClick = onResetClick,
        onRightButtonClick = onResultClick,
        isRightButtonEnabled = uiState.isAllInputFilled,
        leftButtonTextId = R.string.calculator_reset_button_text,
        rightButtonTextId = R.string.calculator_result_button_text,
        snackBarHostState = snackBarHostState
    ) {
        LazyColumn(
            modifier = Modifier.height((uiState.calculatorTextFields.size * 70).dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.calculatorTextFields.size) {
                val calculatorTextField = uiState.calculatorTextFields[it]
                CalculatorLabeledTextField(
                    calculatorType = calculatorTextField.type,
                    input = calculatorTextField.input,
                    onValueChange = { onInputChange(calculatorTextField.type, it) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorScreenPreview() {
    CalculatorScreen(
        padding = PaddingValues(),
        uiState = CalculatorUiState(),
        snackBarHostState = remember { SnackbarHostState() },
        onInputChange = { _, _ -> },
        onResetClick = {},
        onResultClick = {}
    )
}