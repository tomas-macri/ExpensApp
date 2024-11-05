package com.tomasmacri.expensapp.ui.previews.editexpenses

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.tomasmacri.expensapp.domain.model.ExpenseCategory
import com.tomasmacri.expensapp.ui.editexpense.AmountFormField
import com.tomasmacri.expensapp.ui.editexpense.CategoryFormField
import com.tomasmacri.expensapp.ui.editexpense.InputTextFormField
import com.tomasmacri.expensapp.ui.previews.base.BasePreviewWithPadding
import com.tomasmacri.expensapp.ui.theme.getColorsTheme


@Composable
@Preview(showBackground = true)
fun InputTextFormFieldPreview() {
    BasePreviewWithPadding {
        InputTextFormField(colors = getColorsTheme(), value = "MyValue", keyboardController = LocalSoftwareKeyboardController.current, placeholderText = "Enter your text...", titleText = "Field") {}
    }
}

@Composable
@Preview(showBackground = true)
fun AmountFormFieldPreview() {
    BasePreviewWithPadding {
        AmountFormField(colors = getColorsTheme(), amount = TextFieldValue("888.88"), keyboardController = LocalSoftwareKeyboardController.current) {}
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryFormFieldPreview() {
    BasePreviewWithPadding {
        CategoryFormField(colors = getColorsTheme(), category = ExpenseCategory.TRIP.name) {}
    }
}
