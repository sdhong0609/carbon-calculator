package com.hongstudio.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CalculatorData(
    val electricity: Double,
    val gas: Double,
    val water: Double,
    val trash: Double
) {
    val total = electricity * 0.4781 + gas * 2.176 + water * 0.237 + trash * 0.9529
}
