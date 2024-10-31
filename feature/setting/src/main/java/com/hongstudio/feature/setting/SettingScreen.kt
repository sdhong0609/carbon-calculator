package com.hongstudio.feature.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun SettingRoute(padding: PaddingValues) {
    SettingScreen(padding = padding)
}

@Composable
private fun SettingScreen(padding: PaddingValues) {
    Column(
        modifier = Modifier.padding(padding)
    ) {
        Text("Setting Screen")
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    SettingScreen(padding = PaddingValues())
}