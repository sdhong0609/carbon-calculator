package com.hongstudio.feature.calculator.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.core.navigation.CalculatorDataNavType
import com.hongstudio.core.navigation.MainTabRoute
import com.hongstudio.core.navigation.Route
import com.hongstudio.feature.calculator.CalculatorInstructionRoute
import com.hongstudio.feature.calculator.CalculatorResultRoute
import com.hongstudio.feature.calculator.CalculatorRoute
import kotlin.reflect.typeOf

fun NavController.navigateCalculator() {
    navigate(Route.Calculator)
}

fun NavController.navigateCalculatorResult(calculatorData: CalculatorData) {
    navigate(Route.CalculatorResult(calculatorData = calculatorData))
}

fun NavGraphBuilder.calculatorNavGraph(
    padding: PaddingValues,
    navigateToCalculator: () -> Unit,
    navigateToCalculatorResult: (CalculatorData) -> Unit,
) {
    composable<MainTabRoute.CalculatorInstruction> {
        CalculatorInstructionRoute(
            padding = padding,
            navigateToCalculator = navigateToCalculator
        )
    }

    composable<Route.Calculator> {
        CalculatorRoute(
            padding = padding,
            navigateToCalculatorResult = navigateToCalculatorResult
        )
    }

    composable<Route.CalculatorResult>(
        typeMap = mapOf(typeOf<CalculatorData>() to CalculatorDataNavType)
    ) { navBackStackEntry ->
        val calculatorData = navBackStackEntry.toRoute<Route.CalculatorResult>().calculatorData
        CalculatorResultRoute(
            padding = padding,
            calculatorData = calculatorData
        )
    }
}
