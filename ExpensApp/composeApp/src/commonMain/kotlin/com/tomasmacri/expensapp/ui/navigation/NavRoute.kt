package com.tomasmacri.expensapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.ui.graphics.vector.ImageVector
import moe.tlaster.precompose.navigation.Navigator

enum class NavRoute(val route: String, val baseRoute: String, val navigationIcon: ImageVector, val floatingActionButtonIcon: ImageVector?, val onClickFloatingActionButton: (Navigator) -> Unit = {}) {
    HOME("home", "home", Icons.Default.Apps, Icons.Default.Add, {navigator -> navigator.navigate(EDIT_EXPENSE.route) }),
    EDIT_EXPENSE("edit_expense/{id}", "edit_expense/", Icons.Default.ArrowBackIosNew, null);

    companion object {
        fun findByPath(route: String?): NavRoute? {
            return entries.find { route?.startsWith(it.baseRoute) ?: false }
        }
    }
}