package com.tomasmacri.expensapp.data.repository

import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.domain.model.base.Operation
import kotlinx.coroutines.flow.Flow

interface ExpensesRepository {

    fun getAllExpenses(): Flow<Operation<List<Expense>>>

    fun addExpense(expense: Expense): Flow<Operation<Unit>>

    fun updateExpense(expense: Expense): Flow<Operation<Unit>>

    fun getExpense(id: Long): Flow<Operation<Expense>>
}