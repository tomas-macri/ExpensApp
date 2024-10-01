package com.tomasmacri.expensapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.ui.graphics.vector.ImageVector
import moe.tlaster.precompose.navigation.Navigator

enum class NavRoute(val route: String, val title: String, val navigationIcon: ImageVector, val floatingActionButtonIcon: ImageVector?, val onClickFloatingActionButton: (Navigator) -> Unit = {}) {
    HOME("home", "Dashboard", Icons.Default.Apps, Icons.Default.Add, {navigator -> navigator.navigate(ADD_EXPENSE.route) }),
    ADD_EXPENSE("add_expense", "Add Expense", Icons.Default.Add, null),
    EDIT_EXPENSE("edit_expense/{id}", "Update Expense", Icons.Default.Add, null);

    companion object {
        fun findByPath(route: String?): NavRoute? {
            return entries.find { it.route == route }
        }
    }
}