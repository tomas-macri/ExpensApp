package com.tomasmacri.expensapp.domain.usecases.expense

import com.tomasmacri.expensapp.data.repository.ExpensesRepository

class GetAllExpensesUseCase(private val repository: ExpensesRepository) {

    operator fun invoke() = repository.getAllExpenses()

}