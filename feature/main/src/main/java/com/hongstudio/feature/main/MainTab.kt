package com.hongstudio.feature.main

internal enum class MainTab(
    val title: String,
    val iconResId: Int,
) {
    CALCULATOR(
        title = "계산기",
        iconResId = R.drawable.ic_calculator,
    ),
    HISTORY(
        title = "내 계산 보기",
        iconResId = R.drawable.ic_history,
    ),
    SETTING(
        title = "설정",
        iconResId = R.drawable.ic_setting
    )
}
