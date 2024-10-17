package com.tomasmacri.expensapp.data.repository.impl

import com.tomasmacri.expensapp.data.manager.ExpensesManager
import com.tomasmacri.expensapp.data.repository.ExpensesRepository
import com.tomasmacri.expensapp.domain.model.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class ExpensesRepositoryImpl(private val expenseManager: ExpensesManager): ExpensesRepository {
    override fun getAllExpenses(): Flow<List<Expense>> {
        return flowOf(expenseManager.getAllExpenses())
    }

    override fun addExpense(expense: Expense): Flow<Expense> {
        return flowOf(expenseManager.addExpense (expense))
    }

    override fun editExpense(expense: Expense): Flow<Expense> {
        return flowOf(expenseManager.editExpense(expense))
    }

    override fun getExpense(id: Int): Flow<Expense> {
        return flowOf(expenseManager.getExpense(id))
    }
}