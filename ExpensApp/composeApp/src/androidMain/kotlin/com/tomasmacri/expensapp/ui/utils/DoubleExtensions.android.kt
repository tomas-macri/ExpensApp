package com.tomasmacri.expensapp.ui.utils

import java.text.DecimalFormat
import java.util.Locale

actual fun Double.toPriceString(): String {
    val decimalFormat = DecimalFormat.getInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
        minimumIntegerDigits = 1
    }
    return decimalFormat.format(this)
}