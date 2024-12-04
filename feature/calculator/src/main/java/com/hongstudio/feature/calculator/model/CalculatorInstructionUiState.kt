package com.hongstudio.feature.calculator.model

data class CalculatorInstructionUiState(
    val isElectricityChecked: Boolean,
    val isGasChecked: Boolean,
    val isWaterChecked: Boolean,
    val isTrashChecked: Boolean
) {
    companion object {
        val DEFAULT = CalculatorInstructionUiState(
            isElectricityChecked = true,
            isGasChecked = true,
            isWaterChecked = true,
            isTrashChecked = true
        )
    }

    val isAnyChecked = isElectricityChecked || isGasChecked || isWaterChecked || isTrashChecked
}