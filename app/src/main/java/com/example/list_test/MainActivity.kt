package com.example.list_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.list_test.ui.theme.List_testTheme
import java.time.format.TextStyle

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

//    Spacer(modifier = Modifier.height(20.dp))


    Column(modifier = Modifier
        .background(Color.Gray)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)  //odlegl pomiedzy elementami

    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(painter = painterResource(id = R.drawable.main_background),
            contentDescription = "")

        Spacer(modifier = Modifier.height(30.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom)
        {
            Text("Status Connected",
                Modifier
                    .background(Color.DarkGray, shape = RoundedCornerShape(5.dp))
                    .padding(10.dp),
                fontSize = 5.sp, color = Color.White

            )

            Text(
                "Batery",
                Modifier

                    .background(Color.DarkGray, shape = RoundedCornerShape(5.dp))
                    .padding(10.dp),
                fontSize = 5.sp, color = Color.White,

                )

            Text("Device Turn off",
                Modifier
                    .background(Color.DarkGray, shape = RoundedCornerShape(5.dp))
                    .padding(10.dp),
                fontSize = 5.sp, color = Color.White
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

            Text(text = "Last measurements", color = Color.White,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier
                    .width(200.dp)
                    .height(30.dp)
            )
            Spacer(modifier = Modifier.width(50.dp))
            Box() {
                Button(modifier = Modifier
                    .height(28.dp)
                    .width(100.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
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
        Spacer(modifier = Modifier.width(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .width(30.dp)
                .height(300.dp)
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,


            )
        {
            Text(text = "Tutaj twoja lista", fontSize = 40.sp, color = Color.Blue)

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