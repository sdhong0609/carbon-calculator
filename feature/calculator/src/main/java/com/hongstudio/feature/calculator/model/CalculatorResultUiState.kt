package com.hongstudio.feature.calculator.model

data class CalculatorResultUiState(
    val total: String
) {
    companion object {
        val DEFAULT = CalculatorResultUiState(
            total = ""
        )
    }
}
