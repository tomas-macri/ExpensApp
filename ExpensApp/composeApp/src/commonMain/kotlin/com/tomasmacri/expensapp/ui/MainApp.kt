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
import com.tomasmacri.expensapp.ui.navigation.TopBarTitles
import com.tomasmacri.expensapp.ui.theme.ExpensAppTheme
import com.tomasmacri.expensapp.ui.theme.getColorsTheme
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun MainApp() {
    val colors = getColorsTheme()
    PreComposeApp {
        ExpensAppTheme {
            val navigator = rememberNavigator()
            val currentBackStackEntry = navigator.currentEntry.collectAsState(null).value
            val currentNavRoute = NavRoute.findByPath(currentBackStackEntry?.path)
            val topBarTitle = getTopAppBarTitle(currentBackStackEntry, currentNavRoute)
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        elevation = 0.dp,
                        title = {
                            Text(
                                text = topBarTitle,
                                color = colors.textColorExpensApp,
                                fontSize = 24.sp
                            )
                        },
                        backgroundColor = colors.backgroundColorExpensApp,
                        navigationIcon = currentNavRoute?.navigationIcon?.let {
                            {
                                IconButton(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    onClick = { navigator.popBackStack() },
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
                    currentNavRoute?.floatingActionButtonIcon?.let {
                        FloatingActionButton(
                            modifier = Modifier.padding(8.dp),
                            shape = RoundedCornerShape(50),
                            backgroundColor = colors.addIconColorExpensApp,
                            contentColor = Color.White,
                            onClick = {
                                currentNavRoute.onClickFloatingActionButton(navigator)
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

@Composable
fun getTopAppBarTitle(currentBackStackEntry: BackStackEntry?, currentNavRoute: NavRoute?): String {
    return when(currentNavRoute) {
        NavRoute.HOME -> TopBarTitles.HOME.title
        NavRoute.EDIT_EXPENSE -> {
            if (currentBackStackEntry?.path<Int>("id") != null) {
                TopBarTitles.EDIT_EXPENSE.title
            } else {
                TopBarTitles.ADD_EXPENSE.title
            }
        }
        else -> {
            TopBarTitles.HOME.title
        }
    }
}
