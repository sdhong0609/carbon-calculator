package com.hongstudio.feature.calculator.model

data class CalculatorUiState(
    val isElectricityVisible: Boolean,
    val isGasVisible: Boolean,
    val isWaterVisible: Boolean,
    val isTrashVisible: Boolean,
    val electricityInput: String = "",
    val gasInput: String = "",
    val waterInput: String = "",
    val trashInput: String = ""
) {
    companion object {
        val DEFAULT = CalculatorUiState(
            isElectricityVisible = true,
            isGasVisible = true,
            isWaterVisible = true,
            isTrashVisible = true
        )
    }
}
