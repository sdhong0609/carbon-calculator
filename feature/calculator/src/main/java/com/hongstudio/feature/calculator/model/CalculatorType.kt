package com.hongstudio.feature.calculator.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.hongstudio.core.designsystem.theme.Green03
import com.hongstudio.core.designsystem.theme.Mint01
import com.hongstudio.core.designsystem.theme.Red03
import com.hongstudio.core.designsystem.theme.Yellow01
import com.hongstudio.feature.calculator.R

enum class CalculatorType(
    val title: String,
    @DrawableRes val iconResId: Int,
    val iconTint: Color,
    val label: String,
    val unit: String
) {
    ELECTRICITY(
        title = "전기",
        iconResId = R.drawable.ic_electrical_outlet,
        iconTint = Yellow01,
        label = "전기 사용량",
        unit = "kWh"
    ),
    GAS(
        title = "가스",
        iconResId = R.drawable.ic_fire,
        iconTint = Red03,
        label = "가스 사용량",
        unit = "m³"
    ),
    WATER(
        title = "수도",
        iconResId = R.drawable.ic_water_drop,
        iconTint = Mint01,
        label = "수도 사용량",
        unit = "m³"
    ),
    TRASH(
        title = "생활 폐기물",
        iconResId = R.drawable.ic_recycling,
        iconTint = Green03,
        label = "생활 폐기물",
        unit = "L"
    )
}