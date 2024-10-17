package com.tomasmacri.expensapp.data.repository

import com.tomasmacri.expensapp.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface ExpensesRepository {

    fun getAllExpenses(): Flow<List<Expense>>

    fun addExpense(expense: Expense): Flow<Expense>

    fun editExpense(expense: Expense): Flow<Expense>

    fun getExpense(id: Int): Flow<Expense>
}