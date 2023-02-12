package com.example.list_test.list.product

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.list_test.ui.theme.ListTheme

@Composable
fun ProductSignature(signatureName: String, modifier: Modifier) {
    Text(
        modifier = modifier,
        text = signatureName,
        fontSize = 12.sp,
        color = Color.White
    )
}

@Preview
@Composable
fun ProductSignaturePreview() {
    ListTheme {
        ProductSignature("Some signature of product", Modifier.padding(0.dp))
    }
}