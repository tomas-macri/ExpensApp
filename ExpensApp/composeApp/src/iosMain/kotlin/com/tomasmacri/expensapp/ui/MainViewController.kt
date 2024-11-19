package com.tomasmacri.expensapp.ui

import androidx.compose.ui.window.ComposeUIViewController
import com.tomasmacri.expensapp.di.appModule
import com.tomasmacri.expensapp.di.platformModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { MainApp() }

fun initKoin() {
    startKoin {
        modules(appModule.plus(platformModule))
    }.koin
}