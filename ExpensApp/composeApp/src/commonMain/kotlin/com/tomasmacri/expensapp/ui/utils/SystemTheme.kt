package com.tomasmacri.expensapp.ui.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable


object SystemTheme {
    var crossConfigDevice: CrossConfigDevice? = null

    @Composable fun isSystemInDarkMode(): Boolean {
        return crossConfigDevice?.isSystemInDarkMode() ?: isSystemInDarkTheme()
    }
}