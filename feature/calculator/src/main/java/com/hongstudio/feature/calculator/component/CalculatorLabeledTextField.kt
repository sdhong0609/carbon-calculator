package com.hongstudio.feature.calculator.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hongstudio.feature.calculator.model.CalculatorType

@Composable
internal fun CalculatorLabeledTextField(
    calculatorType: CalculatorType,
    input: String,
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
                imageVector = ImageVector.vectorResource(id = calculatorType.iconResId),
                contentDescription = null,
                tint = calculatorType.iconTint
            )
            Text(modifier = Modifier.padding(end = 8.dp), text = calculatorType.label)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CalculatorTextField(
                calculatorType = calculatorType,
                modifier = Modifier.weight(1f),
                input = input,
                onInputChange = onInputChange
            )
            Text(modifier = Modifier.widthIn(min = 56.dp), text = "${calculatorType.unit}/월")
        }
    }
}

@Composable
private fun CalculatorTextField(
    calculatorType: CalculatorType,
    modifier: Modifier,
    input: String,
    onInputChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    val imeAction = if (calculatorType != CalculatorType.TRASH) ImeAction.Next else ImeAction.Done
    val keyboardActions = if (calculatorType != CalculatorType.TRASH) {
        KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
    } else {
        KeyboardActions(onDone = { focusManager.clearFocus() })
    }

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
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions
    )
}

@Preview(showBackground = true)
@Composable
private fun CalculatorLabeledTextFieldPreview() {
    CalculatorLabeledTextField(
        calculatorType = CalculatorType.ELECTRICITY,
        input = "123.456",
        onInputChange = {}
    )
}