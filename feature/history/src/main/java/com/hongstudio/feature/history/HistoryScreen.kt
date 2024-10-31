package com.hongstudio.feature.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun HistoryRoute(padding: PaddingValues) {
    HistoryScreen(padding = padding)
}

@Composable
private fun HistoryScreen(padding: PaddingValues) {
    Column(
        modifier = Modifier.padding(padding)
    ) {
        Text("History Screen")
    }
}

@Preview
@Composable
private fun HistoryScreenPreview() {
    HistoryScreen(padding = PaddingValues())
}