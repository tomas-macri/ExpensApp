package com.tomasmacri.expensapp.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.ui.graphics.vector.ImageVector
import com.tomasmacri.expensapp.domain.model.ExpenseCategory

fun getIconByExpenseCategory(expenseCategory: ExpenseCategory): ImageVector {
    return when(expenseCategory) {
        ExpenseCategory.GROCERIES -> Icons.Default.ShoppingBasket
        ExpenseCategory.FOOD -> Icons.Default.Fastfood
        ExpenseCategory.TRIP -> Icons.Default.Flight
        ExpenseCategory.HOUSE -> Icons.Default.House
        ExpenseCategory.PARTY -> Icons.Default.Celebration
        ExpenseCategory.CONCERTS -> Icons.Default.MusicNote
        ExpenseCategory.OTHER -> Icons.Default.Payment
    }
}