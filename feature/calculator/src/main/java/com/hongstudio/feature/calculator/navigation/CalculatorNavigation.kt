package com.hongstudio.feature.calculator.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.core.model.CalculatorSelected
import com.hongstudio.core.navigation.CalculatorSelectedNavType
import com.hongstudio.core.navigation.MainTabRoute
import com.hongstudio.core.navigation.Route
import com.hongstudio.core.navigation.calculatorDataTypeMap
import com.hongstudio.feature.calculator.CalculatorInstructionRoute
import com.hongstudio.feature.calculator.CalculatorResultRoute
import com.hongstudio.feature.calculator.CalculatorRoute
import kotlin.reflect.typeOf

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
        typeMap = mapOf(typeOf<CalculatorSelected>() to CalculatorSelectedNavType)
    ) { navBackStackEntry ->
        val calculatorSelected = navBackStackEntry.toRoute<Route.Calculator>().calculatorSelected
        CalculatorRoute(
            padding = padding,
            calculatorSelected = calculatorSelected,
            navigateToCalculatorResult = navigateToCalculatorResult
        )
    }

    composable<Route.CalculatorResult>(
        typeMap = calculatorDataTypeMap
    ) {
        CalculatorResultRoute(padding = padding)
    }
}
