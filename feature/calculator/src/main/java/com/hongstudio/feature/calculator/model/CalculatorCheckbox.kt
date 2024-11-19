package com.hongstudio.feature.calculator.model

data class CalculatorCheckbox(
    val label: String,
    val isChecked: Boolean,
    val onCheckedChange: (Boolean) -> Unit
)
