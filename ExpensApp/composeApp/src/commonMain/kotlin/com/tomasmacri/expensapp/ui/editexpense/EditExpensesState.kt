package com.tomasmacri.expensapp.ui.editexpense

import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.domain.model.ExpenseCategory

data class EditExpensesState(
    val originalExpense: Expense? = null,
    val categories: List<ExpenseCategory> = listOf(),
    val loading: Boolean = false
)