package com.tomasmacri.expensapp.ui.editexpense

import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.domain.model.ExpenseCategory

data class EditExpensesState(
    val originalExpense: Expense? = null,
    val expenseUpdated: Expense = Expense(id = 0, "", "", 0.0, ExpenseCategory.OTHER),
    val categories: List<ExpenseCategory> = listOf()
)