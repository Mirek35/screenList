package com.example.list_test.list.product

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.list_test.R
import com.example.list_test.ui.theme.ListTheme

@Composable
fun ProductIcon(recognized: Boolean) {
    if (recognized) {
        MeasurementStatusIcon(R.drawable.ic_success, "Recognized product")
    } else {
        MeasurementStatusIcon(R.drawable.ic_failure, "Unrecognized product")
    }
}

@Composable
private fun MeasurementStatusIcon(iconResource: Int, description: String) {
    Icon(
        painter = painterResource(iconResource),
        modifier = Modifier.padding(start = 20.dp),
        tint = Color.Unspecified,
        contentDescription = description
    )
}

@Preview
@Composable
fun ProductIconRecognizedPreview() {
    ListTheme {
        ProductIcon(recognized = true)
    }
}

@Preview
@Composable
fun ProductIconUnrecognizedPreview() {
    ListTheme {
        ProductIcon(recognized = false)
    }
}