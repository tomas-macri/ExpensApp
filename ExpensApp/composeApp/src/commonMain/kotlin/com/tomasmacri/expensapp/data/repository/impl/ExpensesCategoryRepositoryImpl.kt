package com.tomasmacri.expensapp.data.repository.impl

import com.tomasmacri.expensapp.data.manager.ExpenseCategoriesManager
import com.tomasmacri.expensapp.data.repository.ExpenseCategoryRepository
import com.tomasmacri.expensapp.domain.model.ExpenseCategory
import com.tomasmacri.expensapp.domain.model.base.Operation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExpensesCategoryRepositoryImpl(private val expenseCategoryManager: ExpenseCategoriesManager): ExpenseCategoryRepository {
    override fun getAllExpenseCategories(): Flow<Operation<List<ExpenseCategory>>> {
        return flow {
            emit(Operation.Loading())
            emit(Operation.Success(expenseCategoryManager.getAllCategories()))
        }
    }
}