package com.hongstudio.feature.main

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import com.hongstudio.core.designsystem.theme.Green03
import com.hongstudio.feature.calculator.navigation.calculatorNavGraph
import com.hongstudio.feature.history.navigation.historyNavGraph
import com.hongstudio.feature.setting.navigation.settingNavGraph

@Composable
internal fun MainScreen() {
    val navigator = rememberMainNavigator()

    Scaffold(
        content = { padding ->
            NavHost(
                navController = navigator.navController,
                startDestination = navigator.startDestination,
            ) {
                calculatorNavGraph(padding = padding)
                historyNavGraph(padding = padding)
                settingNavGraph(padding = padding)
            }
        },
        bottomBar = {
            NavigationBar {
                MainTab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = navigator.currentTab == tab,
                        onClick = { navigator.navigate(tab) },
                        label = { Text(text = tab.title) },
                        alwaysShowLabel = false,
                        icon = {
                            Icon(
                                painter = painterResource(tab.iconResId),
                                tint = if (navigator.currentTab == tab) Green03 else MaterialTheme.colorScheme.outline,
                                contentDescription = tab.title
                            )
                        }
                    )
                }
            }
        }
    )
}