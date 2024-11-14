package com.hongstudio.feature.calculator.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
internal fun CalculatorBottomButtons(
    onResetClick: () -> Unit,
    onResultClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ResetButton(onResetClick = onResetClick)
        ResultButton(onResultClick = onResultClick)
    }
}

@Composable
fun ResetButton(onResetClick: () -> Unit) {
    Button(onClick = onResetClick) {
        Text(text = "초기화")
    }
}

@Composable
fun ResultButton(onResultClick: () -> Unit) {
    Button(onClick = onResultClick) {
        Text(text = "결과 보기")
    }
}