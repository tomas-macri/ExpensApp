package com.tomasmacri.expensapp.data.manager

import com.tomasmacri.expensapp.domain.model.ExpenseCategory

object ExpenseCategoriesManager {

    fun getAllCategories(): List<ExpenseCategory> = ExpenseCategory.entries
}