package com.hongstudio.feature.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hongstudio.core.model.CalculatorData
import java.util.Locale

@Composable
internal fun CalculatorResultRoute(
    padding: PaddingValues,
    calculatorData: CalculatorData
) {
    CalculatorResultScreen(
        padding = padding,
        calculatorData = calculatorData
    )
}

@Composable
internal fun CalculatorResultScreen(
    padding: PaddingValues,
    calculatorData: CalculatorData
) {
    val (electricity, gas, water, trash) = calculatorData
    val total = String.format(
        Locale.getDefault(),
        "%,.1f",
        electricity * 0.4781 + gas * 2.176 + water * 0.237 + trash * 0.9529
    )

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("총 CO₂ 발생량")
        Text("$total kg/월")
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorResultScreenPreview() {
    CalculatorResultScreen(
        padding = PaddingValues(),
        calculatorData = CalculatorData(0.0, 0.0, 0.0, 0.0)
    )
}