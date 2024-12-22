package com.hongstudio.feature.calculator.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun CalculatorBottomButtons(
    modifier: Modifier,
    isAllInputFilled: Boolean,
    onResetClick: () -> Unit,
    onResultClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ResetButton(modifier = Modifier.weight(1f), onResetClick = onResetClick)
        ResultButton(
            modifier = Modifier.weight(1f),
            isAllInputFilled = isAllInputFilled,
            onResultClick = onResultClick
        )
    }
}

@Composable
fun ResetButton(modifier: Modifier, onResetClick: () -> Unit) {
    Button(modifier = modifier, onClick = onResetClick) {
        Text(modifier = Modifier.padding(8.dp), text = "초기화")
    }
}

@Composable
fun ResultButton(modifier: Modifier, isAllInputFilled: Boolean, onResultClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onResultClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isAllInputFilled) {
                ButtonDefaults.buttonColors().containerColor
            } else {
                ButtonDefaults.buttonColors().disabledContainerColor
            }
        )
    ) {
        Text(modifier = Modifier.padding(8.dp), text = "결과 보기")
    }
}