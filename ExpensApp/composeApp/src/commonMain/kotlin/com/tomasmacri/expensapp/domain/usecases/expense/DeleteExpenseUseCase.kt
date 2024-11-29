package com.tomasmacri.expensapp.domain.usecases.expense

import com.tomasmacri.expensapp.data.repository.ExpensesRepository
import com.tomasmacri.expensapp.domain.model.base.Operation
import kotlinx.coroutines.flow.Flow

class DeleteExpenseUseCase(private val repository: ExpensesRepository) {

    operator fun invoke(id: Long): Flow<Operation<Unit>> {
        return repository.deleteExpense(id)
    }
}