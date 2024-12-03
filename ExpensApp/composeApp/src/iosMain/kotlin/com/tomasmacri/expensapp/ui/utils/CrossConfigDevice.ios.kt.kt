package com.tomasmacri.expensapp.ui.utils

import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

class CrossConfigDeviceiOS : CrossConfigDevice {
    override fun isSystemInDarkMode(): Boolean {
        val osTheme = UIScreen.mainScreen.traitCollection
        return osTheme.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
    }
}