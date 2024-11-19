package com.tomasmacri.expensapp.data.db.model

import com.tomasmacri.expensapp.db.ExpenseEntity
import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.domain.model.ExpenseCategory

fun ExpenseEntity.toDomain(): Expense {
    return Expense(
        id,
        name,
        description,
        amount,
        ExpenseCategory.valueOf(category)
    )
}