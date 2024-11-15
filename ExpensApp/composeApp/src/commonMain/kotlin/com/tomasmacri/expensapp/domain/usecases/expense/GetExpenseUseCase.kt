package com.tomasmacri.expensapp.domain.usecases.expense

import com.tomasmacri.expensapp.data.repository.ExpensesRepository
import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.domain.model.base.Operation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetExpenseUseCase(private val repository: ExpensesRepository) {

    operator fun invoke(id: Long): Flow<Operation<Expense>> {
        if (id <= 0) {
            return flowOf(Operation.Error("The expense does not exist!"))
        }
        return repository.getExpense(id)
    }

}