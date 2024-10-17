package com.tomasmacri.expensapp.ui.editexpense

import com.tomasmacri.expensapp.data.repository.ExpensesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class EditExpensesViewModel(private val repository: ExpensesRepository): ViewModel() {

    private val _uiState = MutableStateFlow(EditExpensesState())
    val uiState = _uiState.asStateFlow()

    fun getExpense(id: Int) {
        viewModelScope.launch {
            repository.getExpense(id).collect { expense ->
                _uiState.update { it.copy(expenseUpdated = expense) }
            }
        }
    }

    fun addExpense() {
        viewModelScope.launch {
            repository.addExpense(_uiState.value.expenseUpdated).collect { newExpense ->
                _uiState.update { it.copy(actionSuccess = true, expenseUpdated = it.expenseUpdated.copy(id = newExpense.id)) }
            }
        }
    }

    fun updateExpense() {
        viewModelScope.launch {
            repository.editExpense(_uiState.value.expenseUpdated).collect {
                _uiState.update { it.copy(actionSuccess = true) }
            }
        }
    }
}