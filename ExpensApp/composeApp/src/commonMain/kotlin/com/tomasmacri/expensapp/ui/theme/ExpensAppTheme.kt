package com.tomasmacri.expensapp.ui.theme

import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExpensAppTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp)
        )
    ) {
        content()
    }
}

@Composable
fun getColorsTheme(): ExpensAppColorTheme {
    val isDarkMode = false

    val purpleExpensApp = Color(0xFF6A66FF)
    val expenseItemExpensApp = if (isDarkMode) Color(0xFF090808) else Color(0xFFF1F1F1)
    val backgroundColorExpensApp = if (isDarkMode) Color(0xFF1E1C1C) else Color.White
    val textColorExpensApp = if (isDarkMode) Color.Black else Color.White
    val addIconColorExpensApp = if (isDarkMode) purpleExpensApp else Color.Black
    val colorArrowRoundExpensApp = if (isDarkMode) purpleExpensApp else Color.Gray.copy(alpha = 0.2f)

    return ExpensAppColorTheme(
        purpleExpensApp = purpleExpensApp,
        expenseItemExpensApp = expenseItemExpensApp,
        backgroundColorExpensApp = backgroundColorExpensApp,
        textColorExpensApp = textColorExpensApp,
        addIconColorExpensApp = addIconColorExpensApp,
        colorArrowRoundExpensApp = colorArrowRoundExpensApp
    )
}