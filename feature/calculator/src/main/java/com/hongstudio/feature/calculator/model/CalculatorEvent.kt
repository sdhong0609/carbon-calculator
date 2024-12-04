package com.hongstudio.feature.calculator.model

import androidx.annotation.StringRes
import com.hongstudio.core.model.CalculatorData

sealed interface CalculatorEvent {
    data class NavigateToCalculatorResult(val calculatorData: CalculatorData) : CalculatorEvent
    data class ShowSnackBar(@StringRes val messageId: Int) : CalculatorEvent
}