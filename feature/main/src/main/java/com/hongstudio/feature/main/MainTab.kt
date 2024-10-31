package com.hongstudio.feature.main

import com.hongstudio.core.navigation.MainTabRoute

internal enum class MainTab(
    val title: String,
    val iconResId: Int,
    val route: MainTabRoute,
) {
    CALCULATOR(
        title = "계산기",
        iconResId = R.drawable.ic_calculator,
        route = MainTabRoute.Calculator,
    ),
    HISTORY(
        title = "내 계산 보기",
        iconResId = R.drawable.ic_history,
        route = MainTabRoute.History,
    ),
    SETTING(
        title = "설정",
        iconResId = R.drawable.ic_setting,
        route = MainTabRoute.Setting,
    )
}
