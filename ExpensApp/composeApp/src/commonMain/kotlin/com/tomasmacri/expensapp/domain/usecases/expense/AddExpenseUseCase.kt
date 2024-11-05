package com.tomasmacri.expensapp.domain.usecases.expense

import com.tomasmacri.expensapp.data.repository.ExpensesRepository
import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.domain.model.base.Operation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AddExpenseUseCase(private val repository: ExpensesRepository) {

    operator fun invoke(expense: Expense): Flow<Operation<Expense>> {
        if (expense.amount < 0) {
            return flowOf(Operation.Error("The amount of the expense must be positive"))
        }
        if (expense.name.isBlank() || expense.description.isBlank()) {
            return flowOf(Operation.Error("Please fill all the fields"))
        }

        return repository.addExpense(expense)
    }
}