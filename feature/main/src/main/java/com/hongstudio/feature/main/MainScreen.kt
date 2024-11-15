package com.hongstudio.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.hongstudio.core.designsystem.theme.White
import com.hongstudio.feature.calculator.navigation.calculatorNavGraph

@Composable
internal fun MainScreen() {
    val navigator = rememberMainNavigator()

    Scaffold(
        bottomBar = {
//            NavigationBar {
//                MainTab.entries.forEach { tab ->
//                    NavigationBarItem(
//                        selected = navigator.currentTab == tab,
//                        onClick = { navigator.navigate(tab) },
//                        label = { Text(text = tab.title) },
//                        alwaysShowLabel = false,
//                        icon = {
//                            Icon(
//                                painter = painterResource(tab.iconResId),
//                                tint = if (navigator.currentTab == tab) Green03 else MaterialTheme.colorScheme.outline,
//                                contentDescription = tab.title
//                            )
//                        }
//                    )
//                }
//            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
        ) {
            NavHost(
                navController = navigator.navController,
                startDestination = navigator.startDestination,
            ) {
                calculatorNavGraph(
                    padding = innerPadding,
                    navigateToCalculatorResult = navigator::navigateCalculatorResult
                )
//                historyNavGraph(padding = innerPadding)
//                settingNavGraph(padding = innerPadding)
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
