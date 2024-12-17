package com.hongstudio.core.navigation

import com.hongstudio.core.model.CalculatorInputData
import com.hongstudio.core.model.CalculatorType
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data class Calculator(val selectedCalculators: List<CalculatorType>) : Route

    @Serializable
    data class CalculatorResult(val inputCompletedCalculators: List<CalculatorInputData>) : Route
}

sealed interface MainTabRoute {
    @Serializable
    data object CalculatorInstruction : MainTabRoute

    @Serializable
    data object History : MainTabRoute

    @Serializable
    data object Setting : MainTabRoute
}