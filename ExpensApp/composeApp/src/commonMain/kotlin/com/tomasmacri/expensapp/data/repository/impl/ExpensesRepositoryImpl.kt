package com.tomasmacri.expensapp.data.repository.impl

import com.tomasmacri.expensapp.data.manager.ExpensesManager
import com.tomasmacri.expensapp.data.repository.ExpensesRepository
import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.domain.model.base.Operation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExpensesRepositoryImpl(private val expenseManager: ExpensesManager): ExpensesRepository {
    override fun getAllExpenses(): Flow<Operation<List<Expense>>> {
        return flow {
            emit(Operation.Loading())
            emit(Operation.Success(expenseManager.getAllExpenses()))
        }
    }

    override fun addExpense(expense: Expense): Flow<Operation<Expense>> {
        return flow {
            emit(Operation.Loading())
            emit(Operation.Success(expenseManager.addExpense(expense)))
        }
    }

    override fun updateExpense(expense: Expense): Flow<Operation<Expense>> {
        return flow {
            emit(Operation.Loading())
            emit(Operation.Success(expenseManager.editExpense(expense)))
        }
    }

    override fun getExpense(id: Long): Flow<Operation<Expense>> {
        return flow {
            emit(Operation.Loading())
            emit(Operation.Success(expenseManager.getExpense(id)))
        }
    }
}