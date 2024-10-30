package com.hongstudio.feature.history.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hongstudio.core.navigation.MainTabRoute
import com.hongstudio.feature.history.HistoryRoute

fun NavGraphBuilder.historyNavGraph(
    padding: PaddingValues
) {
    composable<MainTabRoute.History> {
        HistoryRoute(padding = padding)
    }
}