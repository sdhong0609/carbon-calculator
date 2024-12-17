package com.hongstudio.feature.calculator.model

import androidx.annotation.StringRes
import com.hongstudio.core.model.CalculatorInputData

sealed interface CalculatorEvent {
    data class NavigateToCalculatorResult(
        val inputCompletedCalculators: List<CalculatorInputData>
    ) : CalculatorEvent

    data class ShowSnackBar(@StringRes val messageId: Int) : CalculatorEvent
}