package com.hongstudio.feature.calculator.model

data class CalculatorInstructionUiState(
    val isElectricityChecked: Boolean = true,
    val isGasChecked: Boolean = true,
    val isWaterChecked: Boolean = true,
    val isTrashChecked: Boolean = true
)