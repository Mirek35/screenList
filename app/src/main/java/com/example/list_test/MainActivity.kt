package com.example.list_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.list_test.ui.theme.List_testTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            List_testTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    TestDropDownMenu()
                }
            }
        }
    }
}

@Composable
fun TestDropDownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Day", "Week", "Month")

    Spacer(modifier = Modifier.height(20.dp))

Box() {

    Column(modifier = Modifier
        .background(Color.LightGray)
        .fillMaxWidth()

    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(painter = painterResource(id = R.drawable.main_background), contentDescription ="drgdrg" )

        Spacer(modifier = Modifier.height(30.dp))

        Row() {
            Text(text = "Ikona, Text")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Ikona, Text")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Ikona, Text")

        }

    }
}


    Row(modifier = Modifier

        .absoluteOffset(x = 23.dp, y = 500.dp)

    ) {
        Icon(
            painter = painterResource(R.drawable.radar2),
            contentDescription = "ikona",
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(15.dp))

        Text(text = "Last measurements", color = Color.White,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .width(200.dp)
                .height(20.dp)
        )
        Spacer(modifier = Modifier.width(50.dp))

        Box {
            Button(modifier = Modifier
                .height(24.dp)
                .width(86.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                onClick = { expanded = !expanded },
                contentPadding = PaddingValues(0.dp))   //text widoczny w Button
            {
                Text("Last day", modifier = Modifier,
                    color = Color.White)
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
                        //do something ...
                    }) {
                        Text(text = label)
                    }
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    List_testTheme {
        TestDropDownMenu()
    }
}