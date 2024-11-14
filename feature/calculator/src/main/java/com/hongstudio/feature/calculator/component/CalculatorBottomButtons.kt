package com.hongstudio.feature.calculator.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun CalculatorBottomButtons(
    onResetClick: () -> Unit,
    onResultClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ResetButton(onResetClick = onResetClick)
        ResultButton(onResultClick = onResultClick)
    }
}

@Composable
fun ResetButton(onResetClick: () -> Unit) {
    Button(onClick = onResetClick) {
        Text(modifier = Modifier.padding(8.dp), text = "초기화")
    }
}

@Composable
fun ResultButton(onResultClick: () -> Unit) {
    Button(onClick = onResultClick) {
        Text(modifier = Modifier.padding(8.dp), text = "결과 보기")
    }
}