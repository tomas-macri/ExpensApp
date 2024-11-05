package com.tomasmacri.expensapp.domain.usecases.expensecategory

import com.tomasmacri.expensapp.data.repository.ExpenseCategoryRepository

class GetAllExpenseCateogriesUseCase(private val repository: ExpenseCategoryRepository) {

    operator fun invoke() = repository.getAllExpenseCategories()

}