package com.hongstudio.feature.calculator.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.core.model.CalculatorSelected
import com.hongstudio.core.navigation.MainTabRoute
import com.hongstudio.core.navigation.Route
import com.hongstudio.core.navigation.TypeMap
import com.hongstudio.feature.calculator.CalculatorInstructionRoute
import com.hongstudio.feature.calculator.CalculatorResultRoute
import com.hongstudio.feature.calculator.CalculatorRoute

fun NavController.navigateCalculator(calculatorSelected: CalculatorSelected) {
    navigate(Route.Calculator(calculatorSelected = calculatorSelected))
}

fun NavController.navigateCalculatorResult(calculatorData: CalculatorData) {
    navigate(Route.CalculatorResult(calculatorData = calculatorData))
}

fun NavGraphBuilder.calculatorNavGraph(
    padding: PaddingValues,
    navigateToCalculator: (CalculatorSelected) -> Unit,
    navigateToCalculatorResult: (CalculatorData) -> Unit,
) {
    composable<MainTabRoute.CalculatorInstruction> {
        CalculatorInstructionRoute(
            padding = padding,
            navigateToCalculator = navigateToCalculator
        )
    }

    composable<Route.Calculator>(
        typeMap = TypeMap.calculatorSelectedTypeMap
    ) { navBackStackEntry ->
        val calculatorSelected = navBackStackEntry.toRoute<Route.Calculator>().calculatorSelected
        CalculatorRoute(
            padding = padding,
            calculatorSelected = calculatorSelected,
            navigateToCalculatorResult = navigateToCalculatorResult
        )
    }

    composable<Route.CalculatorResult>(
        typeMap = TypeMap.calculatorDataTypeMap
    ) {
        CalculatorResultRoute(padding = padding)
    }
}
