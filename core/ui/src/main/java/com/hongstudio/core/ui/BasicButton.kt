package com.hongstudio.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BasicButton(
    modifier: Modifier,
    isButtonEnabled: Boolean = true,
    @StringRes buttonTextId: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isButtonEnabled) {
                ButtonDefaults.buttonColors().containerColor
            } else {
                ButtonDefaults.buttonColors().disabledContainerColor
            }
        )
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(buttonTextId)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BasicButtonPreview() {
    BasicButton(
        modifier = Modifier,
        isButtonEnabled = true,
        buttonTextId = android.R.string.ok,
        onClick = {}
    )
}