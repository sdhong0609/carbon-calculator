package com.hongstudio.feature.calculator

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Checkbox
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
import coil3.compose.AsyncImage
import com.hongstudio.core.model.CalculatorType
import com.hongstudio.feature.calculator.component.SingleButtonScreen
import com.hongstudio.feature.calculator.model.CalculatorInstructionEvent
import com.hongstudio.feature.calculator.model.CalculatorInstructionUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun CalculatorInstructionRoute(
    padding: PaddingValues,
    navigateToCalculator: (List<CalculatorType>) -> Unit,
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
                        navigateToCalculator(event.selectedCalculators)
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
        onCalculatorToggle = viewModel::toggleCalculatorCheckbox,
        onStartCalculatorClick = viewModel::onStartCalculatorClick
    )
}

@Composable
private fun CalculatorInstructionScreen(
    padding: PaddingValues,
    uiState: CalculatorInstructionUiState,
    snackBarHostState: SnackbarHostState,
    onCalculatorToggle: (CalculatorType) -> Unit,
    onStartCalculatorClick: () -> Unit
) {
    SingleButtonScreen(
        padding = padding,
        onButtonClick = onStartCalculatorClick,
        isButtonEnabled = uiState.isAnyChecked,
        buttonTextId = R.string.start_calculator,
        snackBarHostState = snackBarHostState
    ) {
        val checkboxesSize = uiState.calculatorCheckboxes.size

        AsyncImage(
            model = R.drawable.carbon_footprint,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.instruction_1)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.instruction_2)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // TODO: 전체 화면을 LazyVerticalGrid로 구현하는 것도 고려
        LazyVerticalGrid(
            modifier = Modifier.height(
                (if (checkboxesSize % 2 == 0) (checkboxesSize / 2) * 50 else (checkboxesSize / 2 + 1) * 50).dp
            ),
            columns = GridCells.Fixed(checkboxesSize / 2),
            userScrollEnabled = false
        ) {
            items(
                count = checkboxesSize,
                key = { it }
            ) { i ->
                val checkbox = uiState.calculatorCheckboxes[i]

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = checkbox.isChecked,
                        onCheckedChange = {
                            onCalculatorToggle(checkbox.type)
                        }
                    )
                    Text(stringResource(checkbox.type.titleId))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorInstructionScreenPreview() {
    CalculatorInstructionScreen(
        padding = PaddingValues(),
        uiState = CalculatorInstructionUiState(),
        snackBarHostState = remember { SnackbarHostState() },
        onCalculatorToggle = {},
        onStartCalculatorClick = {}
    )
}