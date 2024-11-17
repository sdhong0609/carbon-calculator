package com.hongstudio.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.hongstudio.core.model.CalculatorData
import com.hongstudio.feature.calculator.navigation.navigateCalculator
import com.hongstudio.feature.calculator.navigation.navigateCalculatorResult

internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = MainTab.CALCULATOR.route

    val currentTab: MainTab?
        @Composable get() = MainTab.entries.find { tab ->
            currentDestination?.hierarchy?.any { it.hasRoute(tab.route::class) } == true
        }

    fun navigate(tab: MainTab) {
        val navOption: NavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        navController.navigate(tab.route, navOption)
    }

    fun navigateCalculator() {
        navController.navigateCalculator()
    }

    fun navigateCalculatorResult(calculatorData: CalculatorData) {
        navController.navigateCalculatorResult(calculatorData)
    }
}


@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController = navController)
}