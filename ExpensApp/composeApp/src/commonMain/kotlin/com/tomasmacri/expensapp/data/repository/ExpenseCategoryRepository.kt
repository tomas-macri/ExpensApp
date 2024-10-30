package com.tomasmacri.expensapp.data.repository

import com.tomasmacri.expensapp.domain.model.ExpenseCategory
import com.tomasmacri.expensapp.domain.model.base.Operation
import kotlinx.coroutines.flow.Flow

fun interface ExpenseCategoryRepository {
    fun getAllExpenseCategories(): Flow<Operation<List<ExpenseCategory>>>
}