package com.hongstudio.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CalculatorSelected(
    val isElectricitySelected: Boolean,
    val isGasSelected: Boolean,
    val isWaterSelected: Boolean,
    val isTrashSelected: Boolean
)
