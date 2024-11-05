package com.tomasmacri.expensapp.ui.previews.allexpenses

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.domain.model.ExpenseCategory
import com.tomasmacri.expensapp.ui.allexpenses.AllExpensesHeader
import com.tomasmacri.expensapp.ui.allexpenses.ExpenseItem
import com.tomasmacri.expensapp.ui.allexpenses.ExpensesTotalHeader
import com.tomasmacri.expensapp.ui.previews.base.BasePreviewWithPadding
import com.tomasmacri.expensapp.ui.theme.getColorsTheme

@Composable
@Preview(showBackground = true)
fun ExpensesTotalHeaderPreview() {
    BasePreviewWithPadding {
        ExpensesTotalHeader(colors = getColorsTheme(), totalAmount = 8888.88, currency = "USD")
    }
}

@Composable
@Preview(showBackground = true)
fun AllExpensesHeaderPreview() {
    BasePreviewWithPadding {
        AllExpensesHeader(colors = getColorsTheme()) {}
    }
}

@Composable
@Preview(showBackground = true)
fun ExpenseItemPreview() {
    BasePreviewWithPadding {
        ExpenseItem(colors = getColorsTheme(), expenseItem = Expense(1, "Food", "Date with my girlfriend", 25.0, ExpenseCategory.FOOD)) {}
    }
}
