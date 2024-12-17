package com.hongstudio.feature.calculator.model

import com.hongstudio.core.model.CalculatorType

data class CalculatorUiState(
    val calculatorTextFields: List<CalculatorTextField> = CalculatorType.entries.map {
        CalculatorTextField(
            type = it,
            input = ""
        )
    }
) {
    val isAllInputFilled: Boolean = calculatorTextFields.all { it.input.isNotEmpty() }
}
