package com.hongstudio.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CalculatorData(
    val electricity: Double,
    val gas: Double,
    val water: Double,
    val trash: Double
) {
    companion object {
        fun create(
            electricity: Double,
            gas: Double,
            water: Double,
            trash: Double
        ): CalculatorData {
            return CalculatorData(
                electricity = electricity,
                gas = gas,
                water = water,
                trash = trash
            )
        }
    }
}
