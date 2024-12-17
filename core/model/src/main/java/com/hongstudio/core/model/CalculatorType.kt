package com.hongstudio.core.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.hongstudio.core.designsystem.theme.Green03
import com.hongstudio.core.designsystem.theme.Mint01
import com.hongstudio.core.designsystem.theme.Red03
import com.hongstudio.core.designsystem.theme.Yellow01
import kotlinx.serialization.Serializable

@Serializable
enum class CalculatorType(
    @StringRes val titleId: Int,
    @DrawableRes val iconResId: Int,
    val iconTint: Color,
    @StringRes val labelId: Int,
    @StringRes val unitId: Int,
    val multiply: Double
) {
    ELECTRICITY(
        titleId = R.string.electricity_title,
        iconResId = R.drawable.ic_electrical_outlet,
        iconTint = Yellow01,
        labelId = R.string.electricity_label,
        unitId = R.string.electricity_unit,
        multiply = 0.4781
    ),
    GAS(
        titleId = R.string.gas_title,
        iconResId = R.drawable.ic_fire,
        iconTint = Red03,
        labelId = R.string.gas_label,
        unitId = R.string.gas_unit,
        multiply = 2.176
    ),
    WATER(
        titleId = R.string.water_title,
        iconResId = R.drawable.ic_water_drop,
        iconTint = Mint01,
        labelId = R.string.water_label,
        unitId = R.string.water_unit,
        multiply = 0.237
    ),
    TRASH(
        titleId = R.string.trash_title,
        iconResId = R.drawable.ic_recycling,
        iconTint = Green03,
        labelId = R.string.trash_label,
        unitId = R.string.trash_unit,
        multiply = 0.9529
    )
}