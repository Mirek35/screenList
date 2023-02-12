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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ProductDate(time: LocalDateTime) {
    Text(
        modifier = Modifier.padding(end = 15.dp),
        text = convertTimeToHumanReadableFormat(time),
        fontSize = 13.sp,
        color = Color.White
    )
}

private fun convertTimeToHumanReadableFormat(timeToBeFormatted: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return timeToBeFormatted.format(formatter)
}

@Preview
@Composable
fun ProductDatePreview() {
    ListTheme {
        ProductDate(LocalDateTime.now())
    }
}