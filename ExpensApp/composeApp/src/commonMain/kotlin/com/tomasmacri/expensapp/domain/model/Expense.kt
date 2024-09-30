package com.tomasmacri.expensapp.domain.model

data class Expense(
    val id: Int,
    val name: String,
    val description: String,
    val amount: Double,
    val category: ExpenseCategory
)

val expenses = listOf(
    Expense(1, "Groceries", "Weekly buy", 58.3, ExpenseCategory.GROCERIES),
    Expense(2, "Dinner", "Anniversary", 15.2, ExpenseCategory.FOOD),
    Expense(3, "Football tickets", "Racing Club vs Independiente", 25.3, ExpenseCategory.OTHER),
    Expense(4, "Plane tickets", "Plane tickets to Madrid", 1164.0, ExpenseCategory.TRIP),
    Expense(5, "Plane tickets", "Plane tickets to Madrid", 1164.0, ExpenseCategory.TRIP),
    Expense(6, "Plane tickets", "Plane tickets to Madrid", 1164.0, ExpenseCategory.TRIP),
    Expense(7, "Plane tickets", "Plane tickets to Madrid", 1164.0, ExpenseCategory.TRIP),
    Expense(8, "Plane tickets", "Plane tickets to Madrid", 1164.0, ExpenseCategory.TRIP),
    Expense(9, "Plane tickets", "Plane tickets to Madrid", 1164.0, ExpenseCategory.TRIP),
    Expense(10, "Plane tickets", "Plane tickets to Madrid", 1164.0, ExpenseCategory.TRIP),
)
