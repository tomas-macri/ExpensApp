package com.tomasmacri.expensapp.data.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.tomasmacri.expensapp.db.ExpensAppDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = ExpensAppDatabase.Schema,
            name = "ExpensAppDatabase.db"
        )
    }

}