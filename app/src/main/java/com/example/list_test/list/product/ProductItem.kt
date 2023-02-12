package com.example.list_test.list.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.list_test.ui.theme.ListTheme
import java.time.LocalDateTime

@Composable
fun ProductItem(
    recognized: Boolean,
    productName: String,
    time: LocalDateTime,
    onProductClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clickable(enabled = recognized) { onProductClicked() }
            .padding(start = 20.dp, top = 15.dp, bottom = 15.dp),

        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProductIcon(recognized)
        ProductSignature(productName, Modifier.padding(horizontal = 16.dp))
        Spacer(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        ProductDate(time, Modifier.padding(end = 15.dp))
    }
}


@Preview
@Composable
fun ProductItemPreviewRecognized() {
    ListTheme {
        ProductItem(recognized = true, productName = "Recognized product", time = LocalDateTime.now())
    }
}

@Preview
@Composable
fun ProductItemPreviewNotRecognized() {
    ListTheme {
        ProductItem(recognized = false, productName = "Unrecognized product", time = LocalDateTime.now().minusHours(3))
    }
}