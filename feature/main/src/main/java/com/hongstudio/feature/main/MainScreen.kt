package com.hongstudio.feature.main

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hongstudio.core.designsystem.theme.Green03
import com.hongstudio.feature.calculator.navigation.calculatorNavGraph
import com.hongstudio.feature.history.navigation.historyNavGraph
import com.hongstudio.feature.setting.navigation.settingNavGraph

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        content = { padding ->
            NavHost(
                navController = navController,
                startDestination = MainTab.CALCULATOR.route,
            ) {
                calculatorNavGraph(padding = padding)
                historyNavGraph(padding = padding)
                settingNavGraph(padding = padding)
            }
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                MainTab.entries.forEach { tab ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.hasRoute(tab.route::class) } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = {
                            Text(text = tab.title)
                        },
                        alwaysShowLabel = false,
                        icon = {
                            Icon(
                                painter = painterResource(tab.iconResId),
                                tint = if (selected) Green03 else MaterialTheme.colorScheme.outline,
                                contentDescription = tab.title
                            )
                        }
                    )
                }
            }
        }
    )
}