package com.tomasmacri.expensapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.tomasmacri.expensapp.data.manager.ExpensesManager
import com.tomasmacri.expensapp.data.repository.impl.ExpensesRepositoryImpl
import com.tomasmacri.expensapp.ui.allexpenses.AllExpensesScreen
import com.tomasmacri.expensapp.ui.allexpenses.AllExpensesViewModel
import com.tomasmacri.expensapp.ui.theme.ExpensAppColorTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun Navigation(navigator: Navigator, colors: ExpensAppColorTheme) {
    NavHost(
        modifier = Modifier.background(colors.backgroundColorExpensApp),
        initialRoute = "/home",
        navigator = navigator,
    ) {
        scene(
            route = "/home"
        ) {
            val viewModel: AllExpensesViewModel = viewModel(modelClass = AllExpensesViewModel::class) {
                AllExpensesViewModel(ExpensesRepositoryImpl(ExpensesManager))
            }
            val allExpensesUiState by viewModel.uiState.collectAsStateWithLifecycle()

            AllExpensesScreen(colors = colors, uiState = allExpensesUiState)
        }

        scene(
            route = "/expense_details{id}"
        ) {

        }
    }
}