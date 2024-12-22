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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
        modifier = androidx.compose.ui.Modifier.Companion
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = androidx.compose.ui.Modifier.Companion
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = (52 + 32).dp)
                .padding(all = 16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.Companion.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }
        Box(
            modifier = androidx.compose.ui.Modifier.Companion.align(androidx.compose.ui.Alignment.Companion.BottomCenter)
        ) {
            Button(
                modifier = androidx.compose.ui.Modifier.Companion
                    .align(androidx.compose.ui.Alignment.Companion.BottomCenter)
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                onClick = onButtonClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) {
                        ButtonDefaults.buttonColors().containerColor
                    } else {
                        ButtonDefaults.buttonColors().disabledContainerColor
                    }
                )
            ) {
                Text(
                    modifier = androidx.compose.ui.Modifier.Companion.padding(8.dp),
                    text = androidx.compose.ui.res.stringResource(buttonTextId)
                )
            }
            SnackbarHost(
                modifier = androidx.compose.ui.Modifier.Companion.align(androidx.compose.ui.Alignment.Companion.BottomCenter),
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
        modifier = androidx.compose.ui.Modifier.Companion
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = androidx.compose.ui.Modifier.Companion
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = (52 + 32).dp)
                .padding(all = 16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.Companion.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }
        Box(
            modifier = androidx.compose.ui.Modifier.Companion.align(androidx.compose.ui.Alignment.Companion.BottomCenter)
        ) {
            Button(
                modifier = androidx.compose.ui.Modifier.Companion
                    .align(androidx.compose.ui.Alignment.Companion.BottomCenter)
                    .fillMaxWidth()
                    .background(White)
                    .padding(16.dp),
                onClick = onButtonClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) {
                        ButtonDefaults.buttonColors().containerColor
                    } else {
                        ButtonDefaults.buttonColors().disabledContainerColor
                    }
                )
            ) {
                Text(
                    modifier = androidx.compose.ui.Modifier.Companion.padding(8.dp),
                    text = androidx.compose.ui.res.stringResource(buttonTextId)
                )
            }
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