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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hongstudio.core.designsystem.theme.Gray
import com.hongstudio.core.model.CalculatorType
import com.hongstudio.feature.calculator.util.DecimalFormatter
import com.hongstudio.feature.calculator.util.DecimalInputVisualTransformation

@Composable
internal fun CalculatorLabeledTextField(
    calculatorType: CalculatorType,
    input: String,
    onValueChange: (String) -> Unit
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
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = stringResource(calculatorType.labelId)
            )
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
                onValueChange = onValueChange
            )
            Text(
                modifier = Modifier.widthIn(min = 56.dp),
                text = "${stringResource(calculatorType.unitId)}/월"
            )
        }
    }
}

@Composable
private fun CalculatorTextField(
    calculatorType: CalculatorType,
    modifier: Modifier,
    input: String,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    val imeAction = if (calculatorType != CalculatorType.TRASH) ImeAction.Next else ImeAction.Done
    val keyboardActions = if (calculatorType != CalculatorType.TRASH) {
        KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
    } else {
        KeyboardActions(onDone = { focusManager.clearFocus() })
    }

    val decimalFormatter = DecimalFormatter()

    OutlinedTextField(
        modifier = modifier,
        value = input,
        placeholder = {
            Text(
                text = "숫자 입력",
                color = Gray
            )
        },
        onValueChange = { value ->
            if (value.length <= 10) {
                onValueChange(decimalFormatter.format(value))
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        visualTransformation = DecimalInputVisualTransformation(decimalFormatter)
    )
}

@Preview(showBackground = true)
@Composable
private fun CalculatorLabeledTextFieldPreview() {
    CalculatorLabeledTextField(
        calculatorType = CalculatorType.ELECTRICITY,
        input = "123.456",
        onValueChange = {}
    )
}