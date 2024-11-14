package com.hongstudio.feature.calculator.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hongstudio.core.navigation.MainTabRoute
import com.hongstudio.core.navigation.Route
import com.hongstudio.feature.calculator.CalculatorResultRoute
import com.hongstudio.feature.calculator.CalculatorRoute

fun NavController.navigateCalculatorResult() {
    navigate(Route.CalculatorResult)
}

fun NavGraphBuilder.calculatorNavGraph(
    padding: PaddingValues,
    onResultClick: () -> Unit,
) {
    composable<MainTabRoute.Calculator> {
        CalculatorRoute(
            padding = padding,
            onResultClick = onResultClick
        )
    }

    composable<Route.CalculatorResult> {
        CalculatorResultRoute(padding = padding)
    }
}