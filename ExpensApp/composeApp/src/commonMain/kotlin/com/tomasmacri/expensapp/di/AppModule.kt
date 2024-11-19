package com.tomasmacri.expensapp.di

import com.tomasmacri.expensapp.data.manager.ExpenseCategoriesManager
import com.tomasmacri.expensapp.data.repository.ExpenseCategoryRepository
import com.tomasmacri.expensapp.data.repository.ExpensesRepository
import com.tomasmacri.expensapp.data.repository.impl.ExpensesCategoryRepositoryImpl
import com.tomasmacri.expensapp.data.repository.impl.ExpensesRepositoryImpl
import com.tomasmacri.expensapp.db.ExpensAppDatabase
import com.tomasmacri.expensapp.domain.usecases.expense.AddExpenseUseCase
import com.tomasmacri.expensapp.domain.usecases.expense.GetAllExpensesUseCase
import com.tomasmacri.expensapp.domain.usecases.expense.GetExpenseUseCase
import com.tomasmacri.expensapp.domain.usecases.expense.UpdateExpenseUseCase
import com.tomasmacri.expensapp.domain.usecases.expensecategory.GetAllExpenseCateogriesUseCase
import com.tomasmacri.expensapp.ui.allexpenses.AllExpensesViewModel
import com.tomasmacri.expensapp.ui.editexpense.EditExpensesViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.bind
import org.koin.dsl.module

val managersModule = module {
    single { ExpenseCategoriesManager }.withOptions { createdAtStart() }
}

val repositoryModule = module {
    singleOf(::ExpensesRepositoryImpl).bind(ExpensesRepository::class)
    singleOf(::ExpensesCategoryRepositoryImpl).bind(ExpenseCategoryRepository::class)
    single { ExpensAppDatabase.invoke(get()) }
}

val useCasesModule = module {
    factoryOf(::GetAllExpensesUseCase)
    factoryOf(::GetExpenseUseCase)
    factoryOf(::AddExpenseUseCase)
    factoryOf(::UpdateExpenseUseCase)
    factoryOf(::GetAllExpenseCateogriesUseCase)
}

val viewModelModule = module {
    factoryOf(::AllExpensesViewModel)
    factoryOf(::EditExpensesViewModel)
}

expect val platformModule: Module

val appModule = listOf(managersModule, repositoryModule, useCasesModule, viewModelModule)
