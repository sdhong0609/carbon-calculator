package com.hongstudio.feature.calculator.model

import androidx.annotation.StringRes
import com.hongstudio.core.model.CalculatorSelected

sealed interface CalculatorInstructionEvent {
    data class NavigateToCalculator(
        val calculatorSelected: CalculatorSelected
    ) : CalculatorInstructionEvent

    data class ShowSnackBar(@StringRes val messageId: Int) : CalculatorInstructionEvent
}