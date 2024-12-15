package com.hongstudio.feature.calculator.model

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