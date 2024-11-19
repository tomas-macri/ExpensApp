package com.tomasmacri.expensapp.di

import com.tomasmacri.expensapp.data.db.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { DatabaseDriverFactory(androidContext()).createDriver() }
}