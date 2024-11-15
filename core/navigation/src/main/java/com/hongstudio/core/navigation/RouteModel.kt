package com.hongstudio.core.navigation

import com.hongstudio.core.model.CalculatorData
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data class CalculatorResult(val calculatorData: CalculatorData) : Route
}

sealed interface MainTabRoute {
    @Serializable
    data object Calculator : MainTabRoute

    @Serializable
    data object History : MainTabRoute

    @Serializable
    data object Setting : MainTabRoute
}