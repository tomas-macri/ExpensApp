//
//  ColorExtensions.swift
//  iosApp
//
//  Created by Tomás Ezequiel Macri on 3/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

extension UIColor {
    convenience init(rgb: UInt32) {
        self.init(
            red: CGFloat((rgb & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((rgb & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(rgb & 0x0000FF) / 255.0,
            alpha: 1.0
        )
    }
}
