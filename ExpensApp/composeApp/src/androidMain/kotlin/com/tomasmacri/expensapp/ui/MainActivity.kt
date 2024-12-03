package com.tomasmacri.expensapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import com.tomasmacri.expensapp.ui.theme.getColorsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetStatusBarColor()
            MainApp()
        }
    }

    @Composable
    private fun SetStatusBarColor() {
        val isDarkTheme = isSystemInDarkTheme()
        val colors = getColorsTheme()
        LaunchedEffect(key1 = isDarkTheme) {
            enableEdgeToEdge(
                statusBarStyle = if (isDarkTheme) {
                    SystemBarStyle.dark(colors.backgroundColorExpensApp.toArgb())
                } else {
                    SystemBarStyle.light(colors.backgroundColorExpensApp.toArgb(), colors.blackExpensApp.toArgb())
                }
            )
        }
    }
}