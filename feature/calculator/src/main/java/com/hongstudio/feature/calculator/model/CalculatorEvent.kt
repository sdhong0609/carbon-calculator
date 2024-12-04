package com.hongstudio.feature.calculator.model

import com.hongstudio.core.model.CalculatorData

sealed interface CalculatorEvent {
    data class NavigateToCalculatorResult(val calculatorData: CalculatorData) : CalculatorEvent
}