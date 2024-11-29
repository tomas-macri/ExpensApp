package com.tomasmacri.expensapp.data.repository.impl

import com.tomasmacri.expensapp.data.db.model.toDomain
import com.tomasmacri.expensapp.data.repository.ExpensesRepository
import com.tomasmacri.expensapp.db.ExpensAppDatabase
import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.domain.model.base.Operation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExpensesRepositoryImpl(expenseAppDatabase: ExpensAppDatabase) : ExpensesRepository {

    private val queries = expenseAppDatabase.expenseappdbQueries
    override fun getAllExpenses(): Flow<Operation<List<Expense>>> {
        return flow {
            emit(Operation.Loading())
            emit(Operation.Success(queries.selectAll().executeAsList().map { it.toDomain() }))

        }
    }

    override fun addExpense(expense: Expense): Flow<Operation<Unit>> {
        return flow {
            emit(Operation.Loading())
            emit(
                Operation.Success(
                    queries.transaction {
                        queries.insert(
                            name = expense.name,
                            amount = expense.amount,
                            category = expense.category.name,
                            description = expense.description
                        )
                    }
                )
            )
        }
    }

    override fun updateExpense(expense: Expense): Flow<Operation<Unit>> {
        return flow {
            emit(Operation.Loading())
            emit(
                Operation.Success(
                    queries.transaction {
                        queries.update(
                            name = expense.name,
                            amount = expense.amount,
                            category = expense.category.name,
                            description = expense.description,
                            id = expense.id
                        )
                    }
                )
            )
        }
    }

    override fun getExpense(id: Long): Flow<Operation<Expense>> {
        return flow {
            emit(Operation.Loading())
            val expenseEntity = queries.selectOne(id).executeAsOneOrNull()
            expenseEntity?.let {
                emit(Operation.Success(it.toDomain()))
            } ?: run {
                emit(Operation.Error("The expense does not exist"))
            }
        }
    }

    override fun deleteExpense(id: Long): Flow<Operation<Unit>> {
        return flow {
            emit(Operation.Loading())
            emit(Operation.Success(queries.delete(id)))
        }
    }
}