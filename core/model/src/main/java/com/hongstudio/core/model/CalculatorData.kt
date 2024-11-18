package com.hongstudio.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CalculatorData(
    val electricity: Double,
    val gas: Double,
    val water: Double,
    val trash: Double
)
