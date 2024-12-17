package com.hongstudio.feature.calculator.model

import com.hongstudio.core.model.CalculatorType

data class CalculatorCheckbox(
    val type: CalculatorType,
    val isChecked: Boolean
)
