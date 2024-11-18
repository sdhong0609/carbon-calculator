package com.hongstudio.feature.calculator.model

data class CalculatorUiState(
    val isElectricityVisible: Boolean = true,
    val isGasVisible: Boolean = true,
    val isWaterVisible: Boolean = true,
    val isTrashVisible: Boolean = true,
    val electricityInput: String = "",
    val gasInput: String = "",
    val waterInput: String = "",
    val trashInput: String = ""
)
