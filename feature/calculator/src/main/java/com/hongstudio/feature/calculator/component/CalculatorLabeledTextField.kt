package com.hongstudio.feature.calculator.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
internal fun CalculatorLabeledTextField(
    label: String,
    input: String,
    onInputChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(modifier = Modifier.padding(end = 8.dp), text = label)
        CalculatorTextField(input, onInputChange)
    }
}

@Composable
private fun CalculatorTextField(
    input: String,
    onInputChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.widthIn(max = 200.dp),
        value = input,
        placeholder = { Text("숫자 입력") },
        onValueChange = { value ->
            if (value.count { it == '.' } < 2 && value.length <= 10) {
                onInputChange(value)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
}