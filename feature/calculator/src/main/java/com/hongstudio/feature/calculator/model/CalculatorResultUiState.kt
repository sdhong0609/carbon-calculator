package com.hongstudio.feature.calculator.model

data class CalculatorResultUiState(
    val total: Double
) {
    companion object {
        val DEFAULT = CalculatorResultUiState(
            total = 0.0
        )
    }
}
