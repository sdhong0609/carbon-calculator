package com.hongstudio.feature.calculator.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
internal fun CalculatorLabeledTextField(
    @DrawableRes iconResId: Int,
    iconTint: Color,
    label: String,
    input: String,
    unit: String,
    onInputChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = iconResId),
                contentDescription = null,
                tint = iconTint
            )
            Text(modifier = Modifier.padding(end = 8.dp), text = label)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CalculatorTextField(
                modifier = Modifier.weight(1f),
                input = input,
                onInputChange = onInputChange
            )
            Text(modifier = Modifier.widthIn(min = 56.dp), text = "${unit}/월")
        }
    }
}

@Composable
private fun CalculatorTextField(
    modifier: Modifier, input: String, onInputChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
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
