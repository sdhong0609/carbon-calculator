package com.hongstudio.feature.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.hongstudio.core.model.CalculatorSelected
import com.hongstudio.feature.calculator.model.CalculatorCheckbox
import com.hongstudio.feature.calculator.model.CalculatorInstructionEvent
import com.hongstudio.feature.calculator.model.CalculatorInstructionUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun CalculatorInstructionRoute(
    padding: PaddingValues,
    navigateToCalculator: (CalculatorSelected) -> Unit,
    viewModel: CalculatorInstructionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.event.collectLatest { event ->
                when (event) {
                    is CalculatorInstructionEvent.NavigateToCalculator -> {
                        navigateToCalculator(event.calculatorSelected)
                    }

                    is CalculatorInstructionEvent.ShowSnackBar -> {
                        snackBarHostState.showSnackbar(context.getString(event.messageId))
                    }
                }
            }
        }
    }

    CalculatorInstructionScreen(
        padding = padding,
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        onElectricityCheckedChange = viewModel::onElectricityCheckedChange,
        onGasCheckedChange = viewModel::onGasCheckedChange,
        onWaterCheckedChange = viewModel::onWaterCheckedChange,
        onTrashCheckedChange = viewModel::onTrashCheckedChange,
        onStartCalculatorClick = viewModel::onStartCalculatorClick
    )
}

@Composable
private fun CalculatorInstructionScreen(
    padding: PaddingValues,
    uiState: CalculatorInstructionUiState,
    snackBarHostState: SnackbarHostState,
    onElectricityCheckedChange: (Boolean) -> Unit,
    onGasCheckedChange: (Boolean) -> Unit,
    onWaterCheckedChange: (Boolean) -> Unit,
    onTrashCheckedChange: (Boolean) -> Unit,
    onStartCalculatorClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    val checkboxes = listOf(
        CalculatorCheckbox("전기", uiState.isElectricityChecked, onElectricityCheckedChange),
        CalculatorCheckbox("가스", uiState.isGasChecked, onGasCheckedChange),
        CalculatorCheckbox("수도", uiState.isWaterChecked, onWaterCheckedChange),
        CalculatorCheckbox("생활 폐기물", uiState.isTrashChecked, onTrashCheckedChange)
    )

    Column(
        modifier = Modifier
            .padding(padding)
            .verticalScroll(scrollState)
            .padding(all = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.instruction_1)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.instruction_2)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            modifier = Modifier.height(100.dp), // TODO: 전체 화면을 LazyVerticalGrid로 구현하는 것도 고려
            columns = GridCells.Fixed(checkboxes.size / 2),
            userScrollEnabled = false
        ) {
            items(
                count = checkboxes.size,
                key = { it }
            ) { i ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = checkboxes[i].isChecked,
                        onCheckedChange = checkboxes[i].onCheckedChange
                    )
                    Text(checkboxes[i].label)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onStartCalculatorClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (uiState.isAnyChecked) {
                    ButtonDefaults.buttonColors().containerColor
                } else {
                    ButtonDefaults.buttonColors().disabledContainerColor
                }
            )
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.start_calculator)
            )
        }
        SnackbarHost(snackBarHostState)
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorInstructionScreenPreview() {
    CalculatorInstructionScreen(
        padding = PaddingValues(),
        uiState = CalculatorInstructionUiState.DEFAULT,
        snackBarHostState = remember { SnackbarHostState() },
        onElectricityCheckedChange = {},
        onGasCheckedChange = {},
        onWaterCheckedChange = {},
        onTrashCheckedChange = {},
        onStartCalculatorClick = {}
    )
}