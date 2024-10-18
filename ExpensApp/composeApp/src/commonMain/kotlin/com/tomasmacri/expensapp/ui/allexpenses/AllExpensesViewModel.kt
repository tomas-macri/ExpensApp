package com.tomasmacri.expensapp.ui.allexpenses

import com.tomasmacri.expensapp.data.repository.ExpensesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope

class AllExpensesViewModel(private val expensesRepository: ExpensesRepository): moe.tlaster.precompose.viewmodel.ViewModel() {

    private val _uiState = MutableStateFlow(AllExpensesState())
    val uiState = _uiState.asStateFlow()

    fun getAllExpenses() {
        viewModelScope.launch {
            expensesRepository.getAllExpenses().collect { newExpenseList ->
                _uiState.update {
                    it.copy(expenses = newExpenseList, totalAmount = newExpenseList.sumOf { expense -> expense.amount })
                }
            }
        }
    }
}