package com.hongstudio.core.navigation

import com.hongstudio.core.model.CalculatorData
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Calculator : Route

    @Serializable
    data class CalculatorResult(val calculatorData: CalculatorData) : Route
}

sealed interface MainTabRoute {
    @Serializable
    data object CalculatorInstruction : MainTabRoute

    @Serializable
    data object History : MainTabRoute

    @Serializable
    data object Setting : MainTabRoute
}