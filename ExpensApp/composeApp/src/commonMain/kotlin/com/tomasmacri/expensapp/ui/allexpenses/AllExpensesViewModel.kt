package com.tomasmacri.expensapp.ui.allexpenses

import com.tomasmacri.expensapp.domain.model.base.Operation
import com.tomasmacri.expensapp.domain.usecases.expense.DeleteExpenseUseCase
import com.tomasmacri.expensapp.domain.usecases.expense.GetAllExpensesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class AllExpensesViewModel(
    private val getAllExpensesUseCase: GetAllExpensesUseCase,
    private val deleteExpenseUseCase: DeleteExpenseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AllExpensesState())
    val uiState = _uiState.asStateFlow()

    fun getAllExpenses() {
        viewModelScope.launch {
            getAllExpensesUseCase().collect { operation ->
                when (operation) {
                    is Operation.Error -> Unit
                    is Operation.Loading -> _uiState.update { it.copy(loading = true) }
                    is Operation.Success -> {
                        _uiState.update {
                            it.copy(
                                expenses = operation.data ?: emptyList(),
                                totalAmount = operation.data?.sumOf { expense -> expense.amount } ?: 0.0,
                                loading = false
                            )
                        }
                    }
                }

            }
        }
    }

    fun deleteExpense(id: Long) {
        viewModelScope.launch {
            deleteExpenseUseCase(id).collect { operation ->
                when(operation) {
                    is Operation.Error -> Unit
                    is Operation.Loading -> _uiState.update { it.copy(loading = true) }
                    is Operation.Success -> getAllExpenses()
                }
            }
        }
    }
}