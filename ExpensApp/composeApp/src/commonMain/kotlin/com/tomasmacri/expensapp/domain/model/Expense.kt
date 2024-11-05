package com.tomasmacri.expensapp.domain.model

data class Expense(
    val id: Int,
    val name: String = "",
    val description: String = "",
    val amount: Double = 0.0,
    val category: ExpenseCategory = ExpenseCategory.OTHER
)