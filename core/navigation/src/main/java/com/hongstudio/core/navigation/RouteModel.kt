package com.hongstudio.core.navigation

sealed interface MainTabRoute {
    data object Calculator : MainTabRoute
    data object History : MainTabRoute
    data object Setting : MainTabRoute
}