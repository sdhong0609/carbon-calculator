package com.hongstudio.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CalculatorSelected(
    val isElectricityChecked: Boolean,
    val isGasChecked: Boolean,
    val isWaterChecked: Boolean,
    val isTrashChecked: Boolean
)
