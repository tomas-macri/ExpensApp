package com.tomasmacri.expensapp.ui.editexpense

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomasmacri.expensapp.ui.theme.ExpensAppColorTheme

@Composable
fun EditExpenseScreen(expenseId: Int?, colors: ExpensAppColorTheme, uiState: EditExpensesState, onGetExpenseData: (Int) -> Unit, onSaveExpense: () -> Unit) {
    Column(
        modifier = Modifier.background(colors.backgroundColorExpensApp).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LaunchedEffect(key1 = Unit) {
            expenseId?.let {
                onGetExpenseData(it)
            }
        }

        Text("Amount", color = colors.textColorExpensApp.copy(alpha = 0.5f), fontSize = 20.sp)
        TextField(value = uiState.expenseUpdated.amount.toString(), onValueChange = {}, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
    }
}