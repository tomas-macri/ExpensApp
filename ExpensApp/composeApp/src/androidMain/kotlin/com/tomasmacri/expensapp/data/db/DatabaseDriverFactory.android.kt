package com.tomasmacri.expensapp.data.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.tomasmacri.expensapp.db.ExpensAppDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = ExpensAppDatabase.Schema,
            context = context,
            name = "ExpensAppDatabase.db"
        )
    }

}