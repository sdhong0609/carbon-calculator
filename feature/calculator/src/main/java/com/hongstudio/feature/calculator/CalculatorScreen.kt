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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hongstudio.core.designsystem.theme.Green03
import com.hongstudio.core.designsystem.theme.Mint01
import com.hongstudio.core.designsystem.theme.Red03
import com.hongstudio.core.designsystem.theme.Yellow01
import com.hongstudio.feature.calculator.component.CalculatorLabeledTextField

@Composable
internal fun CalculatorRoute(padding: PaddingValues) {
    CalculatorScreen(padding = padding)
}

@Composable
private fun CalculatorScreen(padding: PaddingValues) {
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
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CalculatorLabeledTextField(
            iconResId = R.drawable.ic_electrical_outlet,
            iconTint = Yellow01,
            label = "전기 사용량",
            unit = "kWh",
            input = electricityInput
        ) { electricityInput = it }
        CalculatorLabeledTextField(
            iconResId = R.drawable.ic_fire,
            iconTint = Red03,
            label = "가스 사용량",
            unit = "m³",
            input = gasInput
        ) { gasInput = it }
        CalculatorLabeledTextField(
            iconResId = R.drawable.ic_water_drop,
            iconTint = Mint01,
            label = "수도 사용량",
            unit = "m³",
            input = waterInput
        ) { waterInput = it }
        CalculatorLabeledTextField(
            iconResId = R.drawable.ic_recycling,
            iconTint = Green03,
            label = "생활 폐기물",
            unit = "L",
            input = trashInput
        ) { trashInput = it }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorScreenPreview() {
    CalculatorScreen(padding = PaddingValues())
}