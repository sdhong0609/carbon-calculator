package com.hongstudio.feature.calculator.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.core.model.CalculatorType
import com.hongstudio.core.navigation.MainTabRoute
import com.hongstudio.core.navigation.Route
import com.hongstudio.core.navigation.TypeMap
import com.hongstudio.feature.calculator.CalculatorInstructionRoute
import com.hongstudio.feature.calculator.CalculatorResultRoute
import com.hongstudio.feature.calculator.CalculatorRoute

fun NavController.navigateCalculator(selectedCalculators: List<CalculatorType>) {
    navigate(Route.Calculator(selectedCalculators = selectedCalculators))
}

fun NavController.navigateCalculatorResult(calculatorData: CalculatorData) {
    navigate(Route.CalculatorResult(calculatorData = calculatorData))
}

fun NavGraphBuilder.calculatorNavGraph(
    padding: PaddingValues,
    navigateToCalculator: (List<CalculatorType>) -> Unit,
    navigateToCalculatorResult: (CalculatorData) -> Unit,
) {
    composable<MainTabRoute.CalculatorInstruction> {
        CalculatorInstructionRoute(
            padding = padding,
            navigateToCalculator = navigateToCalculator
        )
    }

    composable<Route.Calculator>(
        typeMap = TypeMap.selectedCalculatorsTypeMap
    ) {
        CalculatorRoute(
            padding = padding,
            navigateToCalculatorResult = navigateToCalculatorResult
        )
    }

    composable<Route.CalculatorResult>(
        typeMap = TypeMap.calculatorDataTypeMap
    ) {
        CalculatorResultRoute(padding = padding)
    }
}
