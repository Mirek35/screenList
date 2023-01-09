package com.example.list_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
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

//    Spacer(modifier = Modifier.height(20.dp))


    Column(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)  //odlegl pomiedzy elementami

    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.main_background), contentDescription = ""
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                "Status Connected",
                Modifier
                    .background(Color.DarkGray, shape = RoundedCornerShape(5.dp))
                    .padding(10.dp),
                fontSize = 5.sp,
                color = Color.White

            )

            Text(
                "Batery",
                Modifier

                    .background(Color.DarkGray, shape = RoundedCornerShape(5.dp))
                    .padding(10.dp),
                fontSize = 5.sp, color = Color.White,

                )

            Text(
                "Device Turn off",
                Modifier
                    .background(Color.DarkGray, shape = RoundedCornerShape(5.dp))
                    .padding(10.dp),
                fontSize = 5.sp,
                color = Color.White
            )

        }
        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(R.drawable.radar2),
                contentDescription = "ikona",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = "Last measurements",
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier
                    .width(200.dp)
                    .height(30.dp)
            )
            Spacer(modifier = Modifier.width(50.dp))
            Box {
                Button(
                    modifier = Modifier
                        .height(28.dp)
                        .width(100.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                    onClick = { expanded = !expanded },
                    contentPadding = PaddingValues(0.dp)
                )   //text widoczny w Button
                {
                    Text(
                        suggestions[0], modifier = Modifier, color = Color.White
                    )
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
                .background(Color.LightGray)
                .width(30.dp)
                .height(300.dp)
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,


            ) {
            MyList(phoneState)

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
            .fillMaxWidth()
            .wrapContentWidth()
            .padding(25.dp)
    ) {
        Text(text = isOk, fontSize = 10.sp, color = Color.Blue)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = signatureName, fontSize = 10.sp, color = Color.Blue)
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = convertTimeToHumanReadableFormat(time), fontSize = 10.sp,
            color =
            Color.Blue
        )
    }
}

@Composable
private fun convertTimeToHumanReadableFormat(timeToBeFormatted: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return timeToBeFormatted.format(formatter)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    List_testTheme {
        TestDropDownMenu(PhoneState())
    }
}