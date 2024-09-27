package com.tomasmacri.expensapp.domain.model

data class ExpenseItem(
    val name: String,
    val description: String,
    val amount: Double,
    val category: ExpenseCategory
)
