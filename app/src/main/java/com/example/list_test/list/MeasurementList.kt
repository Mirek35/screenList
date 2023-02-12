package com.example.list_test.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.list_test.R
import com.example.list_test.list.product.ProductItem
import com.example.list_test.model.Model
import com.example.list_test.ui.theme.ListTheme

@Composable
fun MeasurementList(model: Model) {
    val groupedProducts = model.groupedProducts.collectAsState()
    Row(
        modifier = Modifier
            .background(
                color = colorResource(R.color.list_background)
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            groupedProducts.value.map {
                item {
                    DateHeader(it.day)
                }
                it.products.map {
                    item {
                        ProductItem(it.recognized, it.signatureName, it.data)
                        Divider(color = Color.Black)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeasurementListPreview() {
    ListTheme {
        Column(modifier = Modifier.clip(RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp))) {
            MeasurementList(model = Model())
        }
    }
}