package com.hongstudio.core.navigation

import kotlinx.serialization.Serializable

sealed interface MainTabRoute {
    @Serializable
    data object Calculator : MainTabRoute

    @Serializable
    data object History : MainTabRoute

    @Serializable
    data object Setting : MainTabRoute
}