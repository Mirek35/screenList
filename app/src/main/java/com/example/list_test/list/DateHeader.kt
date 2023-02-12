package com.example.list_test.list

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
fun DateHeader(dateTime: LocalDateTime, modifier: Modifier) {
    Text(
        modifier = modifier,
        text = converterDateTime(dateTime),
        fontSize = 13.sp,
        color = Color.White
    )
}

fun converterDateTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd EEEE")
    return dateTime.format(formatter)
}

@Preview
@Composable
fun DataHeaderPreview() {
    ListTheme {
        DateHeader(LocalDateTime.now(), Modifier.padding(0.dp))
    }
}