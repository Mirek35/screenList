package com.example.list_test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.list_test.ui.theme.List_testTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TestDropDownMenu(phoneState: PhoneState) {
    Column(modifier = Modifier
        .background(Color.Gray)
        .fillMaxWidth()
        .padding(1.dp),)
    {
        RowInTheDropDownMenu(phoneState)
        MyList(phoneState)
    }
}


@Composable
private fun ColumnScope.RowInTheDropDownMenu(phoneState: PhoneState) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .background(Color.Transparent)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_radar),
                contentDescription = "ikona",
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
                    .padding(start = 10.dp),
                tint = Color.Unspecified
            )
        }
        Box() {
            Text(
                text = "Last measurements",
                color = Color.Black,
                fontFamily = FontFamily.Monospace,
                modifier = androidx.compose.ui.Modifier
                    .width(200.dp)
                    .height(30.dp)
                    .padding(start = 4.dp, top = 2.dp)
            )
        }
        Spacer(modifier = Modifier.width(50.dp))
        Box(
            modifier = androidx.compose.ui.Modifier
                .padding(1.dp)
        ) {
            var expanded by remember { mutableStateOf(false) }
            val suggestions = listOf("Dzisiaj", "Wczoraj")
            Button(
                modifier = androidx.compose.ui.Modifier
                    .height(25.dp)
                    .width(78.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                onClick = { expanded = !expanded },
                contentPadding = PaddingValues(0.dp)
            )   //text widoczny w Button
            {
                Text(suggestions[0], modifier = Modifier, color = Color.White)
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier,
                    Color.White
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                suggestions.forEach { label ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        phoneState.filterClicked(label)
                    }) {
                        Text(text = label)
                    }
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.MyList(phoneState: PhoneState) {
    val allElements = phoneState.allElements.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .fillMaxHeight()
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            allElements.value.map {
                item {
                    MeasurementRow(it.isOk, it.signatureName, it.data)
                }
            }
        }
    }
}

@Composable
private fun MeasurementRow(isOk: String, signatureName: String, time: LocalDateTime) {
    Row(
        modifier = androidx.compose.ui.Modifier
            .padding(0.5.dp)
            .fillMaxWidth()
            .background(Color.Gray)
            .height(65.dp),
        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Box(
            androidx.compose.ui.Modifier
                .width(60.dp)
                .height(60.dp)
                .padding(1.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            if (isOk == "ok") {
                Icon(
                    painter = painterResource(R.drawable.ic_success),
                    modifier = androidx.compose.ui.Modifier.size(40.dp), tint = Color.Unspecified,
                    contentDescription = ""
                )
            } else
                Icon(
                    painter = painterResource(R.drawable.ic_failure),
                    modifier = androidx.compose.ui.Modifier.size(40.dp), tint = Color.Unspecified,
                    contentDescription = ""
                )
        }
        Box(
            androidx.compose.ui.Modifier
                .width(100.dp)
                .height(60.dp)
                .padding(2.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.CenterStart
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = 1.dp),

                text = signatureName,
                fontSize = 12.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.width(140.dp))
        Box(
            androidx.compose.ui.Modifier
                .width(120.dp)
                .height(30.dp)
                .padding(2.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = convertTimeToHumanReadableFormat(time),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
private fun convertTimeToHumanReadableFormat(timeToBeFormatted: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm")
    return timeToBeFormatted.format(formatter)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    List_testTheme {
        TestDropDownMenu(PhoneState())
    }
}