package com.tomasmacri.expensapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.tomasmacri.expensapp.data.manager.ExpensesManager
import com.tomasmacri.expensapp.data.repository.impl.ExpensesRepositoryImpl
import com.tomasmacri.expensapp.ui.allexpenses.AllExpensesScreen
import com.tomasmacri.expensapp.ui.allexpenses.AllExpensesViewModel
import com.tomasmacri.expensapp.ui.editexpense.EditExpenseScreen
import com.tomasmacri.expensapp.ui.editexpense.EditExpensesViewModel
import com.tomasmacri.expensapp.ui.theme.ExpensAppColorTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun Navigation(navigator: Navigator, colors: ExpensAppColorTheme) {
    NavHost(
        modifier = Modifier.background(colors.backgroundColorExpensApp),
        initialRoute = NavRoute.HOME.route,
        navigator = navigator,
    ) {
        scene(
            route = NavRoute.HOME.route
        ) {
            val viewModel: AllExpensesViewModel = viewModel(modelClass = AllExpensesViewModel::class) {
                AllExpensesViewModel(ExpensesRepositoryImpl(ExpensesManager))
            }
            val allExpensesUiState by viewModel.uiState.collectAsStateWithLifecycle()

            AllExpensesScreen(
                colors = colors,
                uiState = allExpensesUiState,
                onGetAllExpenses = {
                   viewModel.getAllExpenses()
                },
                onExpenseSelected = {
                    navigator.navigate("${NavRoute.EDIT_EXPENSE.baseRoute}${it.id}")
                })
        }
        scene(
            route = NavRoute.EDIT_EXPENSE.route
        ) {
            val expenseId = it.path<Int>("id")

            val viewModel: EditExpensesViewModel = viewModel(modelClass = EditExpensesViewModel::class) {
                EditExpensesViewModel(ExpensesRepositoryImpl(ExpensesManager))
            }
            val editExpensesUiState by viewModel.uiState.collectAsStateWithLifecycle()


            EditExpenseScreen(expenseId = expenseId, colors = colors, uiState = editExpensesUiState, onGetExpenseData = { id -> viewModel.getExpense(id) }) {
                if (expenseId == null) {
                    viewModel.addExpense()
                } else {
                    viewModel.updateExpense()
                }
            }
        }

    }
}