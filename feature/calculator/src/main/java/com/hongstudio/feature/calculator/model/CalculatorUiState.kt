package com.hongstudio.feature.calculator.model

data class CalculatorUiState(
    val electricityInput: String = "",
    val gasInput: String = "",
    val waterInput: String = "",
    val trashInput: String = ""
)
