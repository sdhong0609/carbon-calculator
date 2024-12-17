package com.hongstudio.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CalculatorInputData(
    val type: CalculatorType,
    val input: Double
)
