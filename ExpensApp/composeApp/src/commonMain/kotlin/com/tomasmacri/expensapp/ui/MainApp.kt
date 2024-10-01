package com.tomasmacri.expensapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomasmacri.expensapp.ui.navigation.NavRoute
import com.tomasmacri.expensapp.ui.navigation.Navigation
import com.tomasmacri.expensapp.ui.theme.ExpensAppTheme
import com.tomasmacri.expensapp.ui.theme.getColorsTheme
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun MainApp() {
    val colors = getColorsTheme()
    PreComposeApp {
        ExpensAppTheme {
            val navigator = rememberNavigator()
            val currentRoute = NavRoute.findByPath(navigator.currentEntry.collectAsState(null).value?.path)

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        elevation = 0.dp,
                        title = {
                            Text(
                                text = currentRoute?.title ?: "",
                                color = colors.textColorExpensApp,
                                fontSize = 24.sp
                            )
                        },
                        backgroundColor = colors.backgroundColorExpensApp,
                        navigationIcon = currentRoute?.navigationIcon?.let {
                            {
                                IconButton(
                                    modifier = Modifier.padding(start = 16.dp),
                                    onClick = {},
                                ) {
                                    Icon(
                                        imageVector = it,
                                        contentDescription = "Navigation Icon",
                                        tint = colors.addIconColorExpensApp
                                    )
                                }
                            }
                        }
                    )
                },
                floatingActionButton = {
                    currentRoute?.floatingActionButtonIcon?.let {
                        FloatingActionButton(
                            modifier = Modifier.padding(8.dp),
                            shape = RoundedCornerShape(50),
                            backgroundColor = colors.addIconColorExpensApp,
                            contentColor = Color.White,
                            onClick = {
                                currentRoute.onClickFloatingActionButton(navigator)
                            }
                        ) {
                            Icon(
                                imageVector = it,
                                contentDescription = "FloatingActionButton Icon",
                                tint = Color.White
                            )
                        }
                    }
                },
            ) {
                Navigation(navigator = navigator, colors = colors)
            }
        }
    }
}