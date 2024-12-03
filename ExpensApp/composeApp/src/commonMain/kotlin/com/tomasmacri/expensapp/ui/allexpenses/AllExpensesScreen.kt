package com.tomasmacri.expensapp.ui.allexpenses

import LoadingScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.ui.theme.ExpensAppColorTheme
import com.tomasmacri.expensapp.ui.utils.SwipeToDeleteContainer
import com.tomasmacri.expensapp.ui.utils.getIconByExpenseCategory
import com.tomasmacri.expensapp.ui.utils.toPriceString
import expensapp.composeapp.generated.resources.Res
import expensapp.composeapp.generated.resources.money
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun AllExpensesScreen(colors: ExpensAppColorTheme, uiState: AllExpensesState, onGetAllExpenses: () -> Unit, onExpenseSelected: (Expense) -> Unit, onDeleteExpense: (Expense) -> Unit) {
    LaunchedEffect(Unit) {
        onGetAllExpenses()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.background(colors.backgroundColorExpensApp).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            stickyHeader {
                Column(modifier = Modifier.fillParentMaxWidth().background(color = colors.backgroundColorExpensApp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    ExpensesTotalHeader(colors = colors, totalAmount = uiState.totalAmount, currency = "USD")
                    AllExpensesHeader(colors = colors) {}
                }
            }
            if (uiState.expenses.isEmpty()) {
                item {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("You do not have any expenses!", fontWeight = FontWeight.SemiBold, fontSize = 18.sp, textAlign = TextAlign.Center, color = colors.textColorExpensApp)
                            Image(modifier = Modifier.size(150.dp), painter = painterResource(Res.drawable.money), contentDescription = "Money")
                            Text( modifier = Modifier.padding(24.dp), text = "If you need to, you can add it with the button on the bottom right corner", fontSize = 14.sp, textAlign = TextAlign.Center, color = colors.textColorExpensApp)
                        }
                    }
                }
            } else {
                items(uiState.expenses, key = { it.id }) { expenseItem ->
                    SwipeToDeleteContainer(
                        item = expenseItem,
                        onDelete = { onDeleteExpense(expenseItem) }
                    ) {
                        ExpenseItem(colors, expenseItem) {
                            onExpenseSelected(expenseItem)
                        }
                    }
                }
            }

        }
        LoadingScreen(uiState.loading)
    }
}

@Composable
fun ExpenseItem(colors: ExpensAppColorTheme, expenseItem: Expense, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).clickable { onItemClick() },
        backgroundColor = colors.expenseItemExpensApp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(35),
                color = colors.purpleExpensApp
            ) {
                Image(
                    modifier = Modifier.padding(10.dp),
                    imageVector = getIconByExpenseCategory(expenseItem.category),
                    contentDescription = "Expense category",
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            Column(
                modifier = Modifier.padding(start = 8.dp).weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = expenseItem.name, fontWeight = FontWeight.Bold, color = colors.textColorExpensApp, fontSize = 20.sp)
                Text(text = expenseItem.description, color = colors.textColorExpensApp.copy(alpha = 0.5f), fontSize = 16.sp)
            }
            Text(text = "$${expenseItem.amount.toPriceString()}", fontWeight = FontWeight.Bold, color = colors.textColorExpensApp, fontSize = 20.sp)
        }
    }
}

@Composable
fun AllExpensesHeader(colors: ExpensAppColorTheme, onViewAllClicked: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.weight(1f), text = "All Expenses", fontWeight = FontWeight.Bold, color = colors.textColorExpensApp, fontSize = 24.sp)
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = colors.expenseItemExpensApp),
            onClick = { onViewAllClicked() },
            modifier = Modifier.clip(RoundedCornerShape(48.dp)),
            shape = RoundedCornerShape(48.dp),
        ) {
            Text(text = "View all", color = colors.textColorExpensApp)
        }
    }
}

@Composable
fun ExpensesTotalHeader(colors: ExpensAppColorTheme, totalAmount: Double, currency: String) {
    Card(
        shape = RoundedCornerShape(corner = CornerSize(32.dp)),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(colors.blackExpensApp).padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "$${totalAmount.toPriceString()}", fontWeight = FontWeight.ExtraBold, color = colors.whiteExpensApp, fontSize = 24.sp)
            Text(text = currency, color = colors.whiteExpensApp.copy(alpha = 0.6f))
        }
    }
}
