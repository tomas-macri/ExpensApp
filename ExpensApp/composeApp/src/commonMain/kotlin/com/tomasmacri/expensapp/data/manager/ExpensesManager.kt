package com.tomasmacri.expensapp.data.manager

import com.tomasmacri.expensapp.domain.model.Expense
import com.tomasmacri.expensapp.domain.model.ExpenseCategory

object ExpensesManager {
    private val staticExpenses = mutableListOf(
        Expense(1, "Groceries", "Weekly buy", 58.3, ExpenseCategory.GROCERIES),
        Expense(2, "Dinner", "Anniversary", 15.2, ExpenseCategory.FOOD),
        Expense(3, "Football tickets", "Racing Club vs Independiente", 25.3, ExpenseCategory.OTHER),
        Expense(4, "Fleetwood Mac", "Fleetwood Mac concert on Wembley", 123.5, ExpenseCategory.CONCERTS),
        Expense(5, "Plane tickets", "Plane tickets to Madrid", 1164.0, ExpenseCategory.TRIP),
        Expense(6, "Doors", "New doors for the rooms", 503.0, ExpenseCategory.HOUSE),
        Expense(7, "Lautaro's present", "A present for Lautaro's birthday", 35.32, ExpenseCategory.PARTY),
        Expense(8, "Football tickets", "Racing Club vs Boca Juniors", 25.3, ExpenseCategory.OTHER),
        Expense(9, "Tomorrowland", "Tomorrowland W1", 381.5, ExpenseCategory.CONCERTS),
        Expense(10, "Mercadona", "Weekly buy", 34.3, ExpenseCategory.GROCERIES),
    )

    private var expenseId = (staticExpenses.lastOrNull()?.id ?: 0) + 1
    fun getAllExpenses() : List<Expense> = staticExpenses.toList()

    fun addExpense(expense: Expense): Expense {
        val newExpense = expense.copy(id = expenseId++)
        staticExpenses.add(newExpense)
        return newExpense
    }

    fun editExpense(expense: Expense): Expense {
        val indexToReplace = staticExpenses.indexOfFirst { it.id == expense.id }
        if (indexToReplace > 0) {
            staticExpenses[indexToReplace] = expense
        }
        return expense
    }

    fun getExpense(id: Int): Expense {
        return staticExpenses.firstOrNull { it.id == id } ?: Expense(0)
    }

}