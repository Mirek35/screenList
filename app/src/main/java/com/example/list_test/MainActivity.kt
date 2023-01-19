package com.example.list_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val phoneState = PhoneState()
        setContent {
            List_testTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    TestDropDownMenu(phoneState)
                }
            }
        }
    }
}

@Composable
fun TestDropDownMenu(phoneState: PhoneState) {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Dzisiaj", "Wczoraj")

    Column(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(1.dp),

        ) {
        TopIconDevice()
        TreeTextsInTheMiddle()
//        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth()
                .weight(0.3f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .background(Color.Yellow)
            ) {
                Icon(
                    painter = painterResource(R.drawable.radar2),
                    contentDescription = "ikona",
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp),
                    tint = Color.Unspecified
                )
            }
            Box() {
                Text(
                    text = "Last measurements",
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier
                        .width(200.dp)
                        .height(30.dp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(6.dp)
            ) {
                Button(
                    modifier = Modifier
                        .height(28.dp)
                        .width(100.dp),
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
        Spacer(modifier = Modifier.width(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .width(30.dp)
                .height(300.dp)
                .wrapContentHeight()
        ) {
            MyList(phoneState)

        }

    }


}

@Composable
private fun ColumnScope.TreeTextsInTheMiddle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.3f)
            .padding(1.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Status",
            Modifier
                .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
                .padding(10.dp)
                .width(50.dp)
                .height(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 9.sp,
            color = Color.DarkGray,

            )

        Text(
            "Batery",
            Modifier

                .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
                .padding(10.dp)
                .width(50.dp)
                .height(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 5.sp, color = Color.White,

            )

        Text(
            "Device",
            Modifier
                .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
                .padding(10.dp)
                .width(50.dp)
                .height(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 5.sp,
            color = Color.DarkGray
        )

    }
}

@Composable
private fun ColumnScope.TopIconDevice() {
    Row(
        Modifier
            .fillMaxWidth()
            .weight(3f)
            .background(Color.Blue)
            .padding(1.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.main_background),
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun MyList(phoneState: PhoneState) {
    val allElements = phoneState.allElements.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        allElements.value.map {
            item {
                MeasurementRow(it.isOk, it.signatureName, it.data)
            }
        }
    }
}

@Composable
private fun MeasurementRow(isOk: String, signatureName: String, time: LocalDateTime) {

    Row(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .background(Color.Gray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Box(
            Modifier
                .width(130.dp)
                .height(60.dp)
                .padding(2.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            if (isOk == "ok") {
                Icon(
                    painter = painterResource(R.drawable.scanning_ending),
                    modifier = Modifier.size(27.dp), tint = Color.Unspecified,
                    contentDescription = ""
                )
            } else
                Icon(
                    painter = painterResource(R.drawable.scanning_error),
                    modifier = Modifier.size(27.dp), tint = Color.Unspecified,
                    contentDescription = ""
                )
        }
        Box(
            Modifier
                .width(130.dp)
                .height(60.dp)
                .padding(2.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),

                text = signatureName,
                fontSize = 12.sp,
                color = Color.White
            )
        }
        Box(
            Modifier
                .width(120.dp)
                .height(60.dp)
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