package com.hongstudio.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.hongstudio.core.designsystem.theme.CarbonCalculatorTheme
import com.hongstudio.core.designsystem.theme.Green03

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarbonCalculatorTheme(darkTheme = false) {
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                MainTab.entries.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            // navController.navigate(item.title)
                        },
                        label = {
                            Text(text = tab.title)
                        },
                        alwaysShowLabel = false,
                        icon = {
                            Icon(
                                painter = painterResource(tab.iconResId),
                                tint = if (selectedItemIndex == index) Green03 else MaterialTheme.colorScheme.outline,
                                contentDescription = tab.title
                            )
                        }
                    )
                }
            }
        }
    ) {

    }
}
