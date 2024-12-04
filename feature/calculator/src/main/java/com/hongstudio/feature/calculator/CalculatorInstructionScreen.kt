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

@Composable
internal fun CalculatorInstructionRoute(
    padding: PaddingValues,
    navigateToCalculator: (CalculatorSelected) -> Unit,
    viewModel: CalculatorInstructionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.event.collect { event ->
                when (event) {
                    is CalculatorInstructionEvent.NavigateToCalculator -> {
                        navigateToCalculator(event.calculatorSelected)
                    }

                    is CalculatorInstructionEvent.ShowSnackBar -> {
                        snackBarHostState.showSnackbar(event.message)
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
            text = "탄소발자국은 일상 속 다양한 활동에서 얼마나 많은 탄소가 배출되고 있는지 나타내는 지표입니다." +
                    "\n\n탄소발자국 계산을 통해 우리의 일상이 기후변화에 어느 정도로 영향을 미치고 있는지 간접적으로 확인할 수 있습니다.\n"
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "사용 방법\n" +
                    "1. 아래 항목 중 계산기에 입력할 항목을 선택합니다.\n" +
                    "2. 다음 화면에서 각 항목에 대한 사용량을 입력합니다.\n" +
                    "3. 결과 화면에서 총 CO₂ 발생량을 확인합니다."
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
        Button(onClick = onStartCalculatorClick) {
            Text(modifier = Modifier.padding(8.dp), text = "계산기 시작")
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