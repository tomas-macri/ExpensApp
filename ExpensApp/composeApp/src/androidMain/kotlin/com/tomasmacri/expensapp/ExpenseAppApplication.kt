package com.tomasmacri.expensapp

import android.app.Application
import com.tomasmacri.expensapp.di.appModule
import com.tomasmacri.expensapp.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ExpenseAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ExpenseAppApplication)
            androidLogger()
            modules(appModule.plus(platformModule))
        }
    }
}