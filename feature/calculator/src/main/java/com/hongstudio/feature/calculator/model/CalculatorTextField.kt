package com.hongstudio.feature.calculator.model

import com.hongstudio.core.model.CalculatorType

data class CalculatorTextField(
    val type: CalculatorType,
    val input: String
)
