package com.example.list_test

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.list_test.list.ProductList
import com.example.list_test.model.Model
import com.example.list_test.ui.theme.ListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = Model()
        setContent {
            ListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    WholeScreen(model, ::onRecognizedProductClicked)
                }
            }
        }
    }

    private fun onRecognizedProductClicked() {
        Toast.makeText(applicationContext, "Product clicked", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun WholeScreen(model: Model, onProductClicked: () -> Unit = {}) {
    Column(modifier = Modifier.background(Color.Gray).fillMaxWidth(), )
    {
//        TopIconDevice()
//        TreeBoxesTextsInTheMiddle()
        RowInTheDropDownMenu(model)
        ProductList(model, onProductClicked)
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
private fun ColumnScope.TreeBoxesTextsInTheMiddle() {
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
            fontSize = 9.sp, color = Color.Black,

            )

        Text(
            "Device",
            Modifier
                .background(Color.LightGray, shape = RoundedCornerShape(5.dp))
                .padding(10.dp)
                .width(50.dp)
                .height(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 9.sp,
            color = Color.Black
        )

    }
}

@Composable
private fun ColumnScope.RowInTheDropDownMenu(model: Model) {
    Row(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .weight(0.3f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 6.dp)
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
        Box {
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
            var expanded by remember { mutableStateOf(false) }
            val suggestions = listOf("Dzisiaj", "Wczoraj")
            Button(
                modifier = Modifier
                    .height(28.dp)
                    .width(100.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                onClick = { expanded = !expanded },
                contentPadding = PaddingValues(0.dp)
            )
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
                        model.filterClicked(label)
                    }) {
                        Text(text = label)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    ListTheme {
        WholeScreen(Model())
    }
}