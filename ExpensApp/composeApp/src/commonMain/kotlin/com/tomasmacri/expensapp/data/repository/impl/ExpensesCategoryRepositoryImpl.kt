package com.tomasmacri.expensapp.data.repository.impl

import com.tomasmacri.expensapp.data.manager.ExpenseCategoriesManager
import com.tomasmacri.expensapp.data.repository.ExpenseCategoryRepository
import com.tomasmacri.expensapp.domain.model.ExpenseCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ExpensesCategoryRepositoryImpl(private val expenseCategoryManager: ExpenseCategoriesManager): ExpenseCategoryRepository {
    override fun getAllExpenseCategories(): Flow<List<ExpenseCategory>> {
        return flowOf(expenseCategoryManager.getAllCategories())
    }
}