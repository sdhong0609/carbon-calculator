package com.hongstudio.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hongstudio.core.designsystem.theme.White

@Composable
fun DoubleButtonScreen(
    padding: PaddingValues,
    onLeftButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
    isLeftButtonEnabled: Boolean = true,
    isRightButtonEnabled: Boolean = true,
    @StringRes leftButtonTextId: Int,
    @StringRes rightButtonTextId: Int,
    hasKeyboard: Boolean = false,
    snackBarHostState: SnackbarHostState,
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollState = rememberScrollState()

    val keyboardModifier = if (hasKeyboard) {
        Modifier
            .consumeWindowInsets(padding)
            .imePadding()
    } else {
        Modifier
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = keyboardModifier
                .fillMaxSize()
                .verticalScroll(state = scrollState)
                .padding(bottom = (52 + 32).dp)
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BasicButton(
                    modifier = Modifier.weight(1f),
                    isButtonEnabled = isLeftButtonEnabled,
                    buttonTextId = leftButtonTextId,
                    onClick = onLeftButtonClick
                )
                BasicButton(
                    modifier = Modifier.weight(1f),
                    isButtonEnabled = isRightButtonEnabled,
                    buttonTextId = rightButtonTextId,
                    onClick = onRightButtonClick
                )
            }

            SnackbarHost(
                modifier = Modifier.align(Alignment.BottomCenter),
                hostState = snackBarHostState
            )
        }
    }
}

@Composable
fun DoubleButtonScreen(
    padding: PaddingValues,
    onLeftButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
    isLeftButtonEnabled: Boolean = true,
    isRightButtonEnabled: Boolean = true,
    @StringRes leftButtonTextId: Int,
    @StringRes rightButtonTextId: Int,
    hasKeyboard: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollState = rememberScrollState()

    val keyboardModifier = if (hasKeyboard) {
        Modifier
            .consumeWindowInsets(padding)
            .imePadding()
    } else {
        Modifier
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = keyboardModifier
                .fillMaxSize()
                .verticalScroll(state = scrollState)
                .padding(bottom = (52 + 32).dp)
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BasicButton(
                    modifier = Modifier.weight(1f),
                    isButtonEnabled = isLeftButtonEnabled,
                    buttonTextId = leftButtonTextId,
                    onClick = onLeftButtonClick
                )
                BasicButton(
                    modifier = Modifier.weight(1f),
                    isButtonEnabled = isRightButtonEnabled,
                    buttonTextId = rightButtonTextId,
                    onClick = onRightButtonClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DoubleButtonScreenPreview() {
    DoubleButtonScreen(
        padding = PaddingValues(),
        onLeftButtonClick = {},
        onRightButtonClick = {},
        leftButtonTextId = android.R.string.cancel,
        rightButtonTextId = android.R.string.ok,
        content = {}
    )
}