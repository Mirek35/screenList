package com.example.list_test.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.list_test.R
import com.example.list_test.model.Model
import com.example.list_test.ui.theme.MaterialListTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MeasurementList(model: Model) {
    val groupedProducts = model.groupedProducts.collectAsState()
    Row(
        modifier = Modifier.background(color = colorResource(R.color.list_background))
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            groupedProducts.value.map {
                item {
                    DateHeader(it.day)
                }
                it.products.map {
                    item {
                        MeasurementItem(it.recognized, it.signatureName, it.data)
                        Divider(color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
private fun DateHeader(dateTime: LocalDateTime) {
    Text(
        modifier = Modifier.padding(start = 20.dp, top = 7.dp),
        text = converterDateTime(dateTime),
        fontSize = 13.sp,
        color = Color.White
    )
}

fun converterDateTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd EEEE")
    return dateTime.format(formatter)
}

@Composable
private fun MeasurementItem(recognized: Boolean, signatureName: String, time: LocalDateTime) {
    Row(
        modifier = Modifier.padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MeasurementIcon(recognized)
        SignatureEntry(signatureName)
        Spacer(modifier = Modifier
            .weight(1f)
            .fillMaxHeight())
        MeasurementDate(time)
    }
}

@Composable
private fun MeasurementDate(time: LocalDateTime) {
    Text(
        modifier = Modifier.padding(end = 15.dp),
        text = convertTimeToHumanReadableFormat(time),
        fontSize = 13.sp,
        color = Color.White
    )
}

@Composable
private fun SignatureEntry(signatureName: String) {
    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = signatureName,
        fontSize = 12.sp,
        color = Color.White
    )
}

@Composable
private fun MeasurementIcon(recognized: Boolean) {
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
        modifier = Modifier
            .padding(start = 20.dp),
        tint = Color.Unspecified,
        contentDescription = description
    )
}

@Preview(showBackground = true)
@Composable
fun MeasurementListPreview() {
    MaterialListTheme {
        Column(modifier = Modifier.clip(RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp))) {
            MeasurementList(model = Model())
        }
    }
}

private fun convertTimeToHumanReadableFormat(timeToBeFormatted: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return timeToBeFormatted.format(formatter)
}