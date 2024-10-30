package com.hongstudio.feature.calculator.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hongstudio.core.navigation.MainTabRoute
import com.hongstudio.feature.calculator.CalculatorRoute

fun NavGraphBuilder.calculatorNavGraph(
    padding: PaddingValues
) {
    composable<MainTabRoute.Calculator> {
        CalculatorRoute(padding = padding)
    }
}