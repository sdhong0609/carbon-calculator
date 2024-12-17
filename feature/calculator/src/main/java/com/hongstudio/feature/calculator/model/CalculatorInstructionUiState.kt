package com.hongstudio.feature.calculator.model

import com.hongstudio.core.model.CalculatorType

data class CalculatorInstructionUiState(
    val calculatorCheckboxes: List<CalculatorCheckbox> = CalculatorType.entries.map {
        CalculatorCheckbox(
            type = it,
            isChecked = true
        )
    }
) {
    val isAnyChecked = calculatorCheckboxes.any { it.isChecked }
}