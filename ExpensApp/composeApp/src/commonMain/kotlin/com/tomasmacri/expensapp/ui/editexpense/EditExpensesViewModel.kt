package com.tomasmacri.expensapp.ui.editexpense

import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.domain.model.base.Operation
import com.tomasmacri.expensapp.domain.usecases.expense.AddExpenseUseCase
import com.tomasmacri.expensapp.domain.usecases.expense.GetExpenseUseCase
import com.tomasmacri.expensapp.domain.usecases.expense.UpdateExpenseUseCase
import com.tomasmacri.expensapp.domain.usecases.expensecategory.GetAllExpenseCateogriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class EditExpensesViewModel(
    private val getExpenseUseCase: GetExpenseUseCase,
    private val getAllExpenseCateogriesUseCase: GetAllExpenseCateogriesUseCase,
    private val addExpenseUseCase: AddExpenseUseCase,
    private val updateExpenseUseCase: UpdateExpenseUseCase): ViewModel() {

    private val _uiState = MutableStateFlow(EditExpensesState())
    val uiState = _uiState.asStateFlow()

    fun getExpense(id: Long) {
        viewModelScope.launch {
            getExpenseUseCase(id).collect { operation ->
                when(operation) {
                    is Operation.Error -> Unit
                    is Operation.Loading -> _uiState.update { it.copy(loading = true) }
                    is Operation.Success -> {
                        _uiState.update { it.copy(originalExpense = operation.data, loading = false) }
                    }
                }
            }
        }
    }

    fun getAllCategories() {
        viewModelScope.launch {
            getAllExpenseCateogriesUseCase().collect { operation ->
                when(operation) {
                    is Operation.Error -> Unit
                    is Operation.Loading -> _uiState.update { it.copy(loading = true) }
                    is Operation.Success -> _uiState.update { it.copy(categories = operation.data ?: emptyList(), loading = false) }
                }
            }
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            addExpenseUseCase(expense).collect { operation ->
                when (operation) {
                    is Operation.Error -> Unit
                    is Operation.Loading -> _uiState.update { it.copy(loading = true) }
                    is Operation.Success -> _uiState.update { it.copy(loading = false) }
                }
            }
        }
    }

    fun updateExpense(expense: Expense) {
        viewModelScope.launch {
            updateExpenseUseCase(expense).collect { operation ->
                when (operation) {
                    is Operation.Error -> Unit
                    is Operation.Loading -> _uiState.update { it.copy(loading = true) }
                    is Operation.Success -> _uiState.update { it.copy(loading = false) }
                }
            }
        }
    }
}