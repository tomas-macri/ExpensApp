package com.tomasmacri.expensapp.ui.allexpenses

import com.tomasmacri.expensapp.domain.model.Expense

data class AllExpensesState(
    val expenses: List<Expense> = emptyList(),
    val totalAmount: Double = 0.0
)
