package com.hongstudio.feature.setting.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hongstudio.core.navigation.MainTabRoute
import com.hongstudio.feature.setting.SettingRoute

fun NavGraphBuilder.settingNavGraph(
    padding: PaddingValues
) {
    composable<MainTabRoute.Setting> {
        SettingRoute(padding = padding)
    }
}