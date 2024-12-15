package com.hongstudio.feature.calculator.model

import androidx.annotation.StringRes
import com.hongstudio.core.model.CalculatorType

sealed interface CalculatorInstructionEvent {
    data class NavigateToCalculator(
        val selectedCalculators: List<CalculatorType>
    ) : CalculatorInstructionEvent

    data class ShowSnackBar(@StringRes val messageId: Int) : CalculatorInstructionEvent
}