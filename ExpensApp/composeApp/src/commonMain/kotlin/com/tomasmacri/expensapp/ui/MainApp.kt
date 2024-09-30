package com.tomasmacri.expensapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.tomasmacri.expensapp.data.manager.ExpensesManager
import com.tomasmacri.expensapp.data.repository.impl.ExpensesRepositoryImpl
import com.tomasmacri.expensapp.ui.allexpenses.AllExpensesScreen
import com.tomasmacri.expensapp.ui.allexpenses.AllExpensesViewModel
import com.tomasmacri.expensapp.ui.navigation.Navigation
import com.tomasmacri.expensapp.ui.theme.ExpensAppTheme
import com.tomasmacri.expensapp.ui.theme.getColorsTheme
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun MainApp() {
    PreComposeApp {
        ExpensAppTheme {
            Navigation(navigator = Navigator(), colors = getColorsTheme())
        }
    }
}