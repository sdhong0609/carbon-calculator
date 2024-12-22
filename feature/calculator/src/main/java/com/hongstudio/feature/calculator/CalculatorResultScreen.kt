package com.hongstudio.feature.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil3.compose.AsyncImage
import com.hongstudio.core.designsystem.theme.CarbonCalculatorTheme
import com.hongstudio.core.designsystem.theme.White
import com.hongstudio.feature.calculator.model.CalculatorResultEvent
import com.hongstudio.feature.calculator.model.CalculatorResultUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun CalculatorResultRoute(
    padding: PaddingValues,
    popUntilCalculatorInstruction: () -> Unit,
    viewModel: CalculatorResultViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.event.collectLatest { event ->
                when (event) {
                    is CalculatorResultEvent.PopUntilCalculatorInstruction -> {
                        popUntilCalculatorInstruction()
                    }
                }
            }
        }
    }

    CalculatorResultScreen(
        padding = padding,
        uiState = uiState,
        onRestartClick = viewModel::onRestartClick
    )
}

@Composable
private fun CalculatorResultScreen(
    padding: PaddingValues,
    uiState: CalculatorResultUiState,
    onRestartClick: () -> Unit
) {
    val totalValue = "%,.1f".format(uiState.total)
    val formattedTotal = if (totalValue.endsWith(".0")) {
        totalValue.dropLast(2)
    } else {
        totalValue
    }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = (52 + 32).dp)
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.total_co2_title),
                style = CarbonCalculatorTheme.typography.headlineMediumB
            )
            AsyncImage(
                modifier = Modifier.padding(16.dp),
                model = R.drawable.co2_neutral,
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.total_co2, formattedTotal),
                style = CarbonCalculatorTheme.typography.headlineMediumB
            )
        }
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                onClick = onRestartClick
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(R.string.calculator_restart_button_text)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorResultScreenPreview() {
    CalculatorResultScreen(
        padding = PaddingValues(),
        uiState = CalculatorResultUiState(),
        onRestartClick = {}
    )
}