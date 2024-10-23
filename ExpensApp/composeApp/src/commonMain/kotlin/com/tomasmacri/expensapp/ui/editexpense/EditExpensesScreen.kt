package com.tomasmacri.expensapp.ui.editexpense

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomasmacri.expensapp.domain.model.ExpenseCategory
import com.tomasmacri.expensapp.ui.editexpense.model.EditExpenseFormFields
import com.tomasmacri.expensapp.ui.theme.ExpensAppColorTheme
import com.tomasmacri.expensapp.ui.utils.getIconByExpenseCategory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditExpenseScreen(
    expenseId: Int?,
    colors: ExpensAppColorTheme,
    uiState: EditExpensesState,
    onGetInitialData: (Int?) -> Unit,
    onUiChangeExpenseDate: (Any?, EditExpenseFormFields) -> Unit,
    onSaveExpense: () -> Unit
) {
    var priceTextfield by remember { mutableStateOf(TextFieldValue("", TextRange(0))) }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(sheetState.targetValue) {
        if (sheetState.targetValue == ModalBottomSheetValue.Expanded) {
            keyboardController?.hide()
        }
    }
    LaunchedEffect(key1 = Unit) {
        onGetInitialData(expenseId)
    }
    LaunchedEffect(key1 = uiState.originalExpense?.amount) {
        val newAmount = (uiState.originalExpense?.amount ?: 0.0).toString()
        priceTextfield = TextFieldValue(newAmount, TextRange(newAmount.length-1))
    }

    ModalBottomSheetLayout(
        sheetContent = {
            CategoriesModalBottomSheet(uiState.categories) {
                onUiChangeExpenseDate(it, EditExpenseFormFields.EXPENSE_CATEGORY)
                scope.launch {
                    sheetState.hide()
                }
            }
        },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.background(colors.backgroundColorExpensApp).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            AmountFormField(colors = colors, amount = priceTextfield, keyboardController = keyboardController) { newTextFieldValue ->
                priceTextfield = moneyFilter(priceTextfield, newTextFieldValue)
            }
            CategoryFormField(colors = colors, category = uiState.expenseUpdated.category.name) {
                scope.launch {
                    sheetState.show()
                }
            }
            DescriptionFormField(colors = colors, description = uiState.expenseUpdated.description, keyboardController = keyboardController) {
                onUiChangeExpenseDate(it, EditExpenseFormFields.EXPENSE_DESCRIPTION)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(45)),
                enabled = priceTextfield.text.toDoubleOrNull() != null,
                onClick = {
                    onUiChangeExpenseDate(priceTextfield.text.toDoubleOrNull(), EditExpenseFormFields.EXPENSE_AMOUNT)
                    onSaveExpense()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = colors.purpleExpensApp, contentColor = Color.White)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Save expense",
                    fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun CategoriesModalBottomSheet(
    categories: List<ExpenseCategory>,
    onCategorySelected: (ExpenseCategory) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(16.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        items(categories, key = { it.ordinal }) { categoryItem ->
            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
                    onCategorySelected(categoryItem)
                },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(40.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    imageVector = getIconByExpenseCategory(categoryItem),
                    contentDescription = categoryItem.name
                )
                Text(categoryItem.name)
            }
        }
    }
}

@Composable
fun AmountFormField(colors: ExpensAppColorTheme, amount: TextFieldValue, keyboardController: SoftwareKeyboardController?, onAmountChanged: (TextFieldValue) -> Unit) {
    TitleWithFieldEditExpenseForm(colors = colors, titleText = "Amount") {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                modifier = Modifier.weight(1f),
                value = amount,
                onValueChange = {
                    onAmountChanged(it)
                },
                placeholder = { Text("0.00", fontSize = 35.sp, fontWeight = FontWeight.SemiBold) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = colors.textColorExpensApp,
                    backgroundColor = colors.backgroundColorExpensApp,
                    focusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.ExtraBold)
            )
            Text("USD", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = colors.textColorExpensApp.copy(alpha = 0.5f))
        }
        Divider(color = colors.textColorExpensApp, thickness = 2.dp)
    }
}

@Composable
fun CategoryFormField(colors: ExpensAppColorTheme, category: String, openBottomSheet: () -> Unit) {
    TitleWithFieldEditExpenseForm(colors = colors, titleText = "Category") {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = category,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            IconButton(
                modifier = Modifier.clip(RoundedCornerShape(percent = 35)).background(colors.colorArrowRoundExpensApp),
                onClick = {
                    openBottomSheet()
                },
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Open categories bottom sheet",
                    tint = colors.textColorExpensApp
                )
            }
        }
        Divider(color = colors.textColorExpensApp, thickness = 2.dp)
    }
}


@Composable
fun DescriptionFormField(colors: ExpensAppColorTheme, description: String, keyboardController: SoftwareKeyboardController?, onDescriptionChanged: (String) -> Unit) {
    TitleWithFieldEditExpenseForm(colors = colors, titleText = "Description") {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = {
                onDescriptionChanged(it)
            },
            placeholder = { Text("Enter the description of the expense...") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colors.textColorExpensApp,
                backgroundColor = colors.backgroundColorExpensApp,
                focusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
        )
        Divider(color = colors.textColorExpensApp, thickness = 2.dp)
    }
}

@Composable
fun TitleWithFieldEditExpenseForm(colors: ExpensAppColorTheme, titleText: String, formField: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = titleText, color = colors.textColorExpensApp.copy(alpha = 0.5f), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        formField()
    }

}

private fun moneyFilter(currentText: TextFieldValue, newText: TextFieldValue): TextFieldValue {
    return if (newText.text.all { it.isDigit() || isDecimalSymbol(it) }) {
        newText.text.indices.forEach { charIndex ->
            if (isDecimalSymbol(newText.text[charIndex]) && newText.text.substring(charIndex + 1).length > 2) {
                return TextFieldValue(currentText.text, TextRange(newText.selection.start - 1))
            }
        }
        newText
    } else  {
        TextFieldValue(currentText.text, TextRange(newText.selection.start-1))
    }
}

private fun isDecimalSymbol(character: Char) = character == '.' || character == ','
