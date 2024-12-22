package com.hongstudio.core.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun SingleButtonScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit,
    isButtonEnabled: Boolean = true,
    @StringRes buttonTextId: Int,
    snackBarHostState: SnackbarHostState,
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
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
            BasicButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                isButtonEnabled = isButtonEnabled,
                buttonTextId = buttonTextId,
                onClick = onButtonClick
            )
            SnackbarHost(
                modifier = Modifier.align(Alignment.BottomCenter),
                hostState = snackBarHostState
            )
        }
    }
}

@Composable
fun SingleButtonScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit,
    isButtonEnabled: Boolean = true,
    @StringRes buttonTextId: Int,
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
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
            BasicButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                isButtonEnabled = isButtonEnabled,
                buttonTextId = buttonTextId,
                onClick = onButtonClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SingleButtonScreenPreview() {
    SingleButtonScreen(
        padding = PaddingValues(),
        onButtonClick = {},
        isButtonEnabled = true,
        buttonTextId = android.R.string.ok,
        content = {}
    )
}