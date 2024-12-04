package com.hongstudio.feature.calculator.model

import com.hongstudio.core.model.CalculatorSelected

sealed interface CalculatorInstructionEvent {
    data class NavigateToCalculator(
        val calculatorSelected: CalculatorSelected
    ) : CalculatorInstructionEvent

    data class ShowSnackBar(val message: String) : CalculatorInstructionEvent
}