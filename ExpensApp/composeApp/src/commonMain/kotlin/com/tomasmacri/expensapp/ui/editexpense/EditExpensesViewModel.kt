package com.tomasmacri.expensapp.ui.editexpense

import com.tomasmacri.expensapp.data.repository.ExpenseCategoryRepository
import com.tomasmacri.expensapp.data.repository.ExpensesRepository
import com.tomasmacri.expensapp.domain.model.ExpenseCategory
import com.tomasmacri.expensapp.ui.editexpense.model.EditExpenseFormFields
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class EditExpensesViewModel(private val expensesRepository: ExpensesRepository, private val categoryRepository: ExpenseCategoryRepository): ViewModel() {

    private val _uiState = MutableStateFlow(EditExpensesState())
    val uiState = _uiState.asStateFlow()

    fun getExpense(id: Int) {
        viewModelScope.launch {
            expensesRepository.getExpense(id).collect { expense ->
                _uiState.update { it.copy(originalExpense = expense, expenseUpdated = expense) }
            }
        }
    }

    fun getAllCategories() {
        viewModelScope.launch {
            categoryRepository.getAllExpenseCategories().collect {categories ->
                _uiState.update { it.copy(categories = categories) }
            }
        }
    }

    fun addExpense() {
        viewModelScope.launch {
            expensesRepository.addExpense(_uiState.value.expenseUpdated).collect {
            }
        }
    }

    fun updateExpense() {
        viewModelScope.launch {
            expensesRepository.editExpense(_uiState.value.expenseUpdated).collect {
            }
        }
    }

    fun uiChangeExpenseData(newValue: Any, expenseFieldForm: EditExpenseFormFields) {
        when(expenseFieldForm) {
            EditExpenseFormFields.EXPENSE_AMOUNT -> {
                if (newValue is Double) {
                    _uiState.update { it.copy(expenseUpdated = it.expenseUpdated.copy(amount = newValue)) }
                }
            }
            EditExpenseFormFields.EXPENSE_CATEGORY -> {
                if (newValue is ExpenseCategory){
                    _uiState.update { it.copy(expenseUpdated = it.expenseUpdated.copy(category = newValue)) }
                }
            }
            EditExpenseFormFields.EXPENSE_DESCRIPTION -> {
                if (newValue is String) {
                    _uiState.update { it.copy(expenseUpdated = it.expenseUpdated.copy(description = newValue)) }
                }
            }
        }
    }
}