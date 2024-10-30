package com.tomasmacri.expensapp.ui.previews.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BasePreviewWithPadding(padding: Int = 10, content: @Composable () -> Unit){
    Box(modifier = Modifier.padding(padding.dp)) {
        content()
    }
}