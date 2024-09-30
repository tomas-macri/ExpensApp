package com.tomasmacri.expensapp.data.repository

import com.tomasmacri.expensapp.domain.model.ExpenseCategory
import kotlinx.coroutines.flow.Flow

fun interface ExpenseCategoryRepository {
    fun getAllExpenseCategories(): Flow<List<ExpenseCategory>>
}