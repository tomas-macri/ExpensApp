package com.tomasmacri.expensapp.ui.utils

import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.currentLocale

actual fun Double.toPriceString(): String {
    val formatter = NSNumberFormatter()
    formatter.locale = NSLocale.currentLocale
    formatter.minimumFractionDigits = 2u
    formatter.maximumFractionDigits = 2u
    formatter.numberStyle = 1u
    formatter.minimumIntegerDigits = 1u //Decimal
    return formatter.stringFromNumber(NSNumber(this)) ?: ""
}