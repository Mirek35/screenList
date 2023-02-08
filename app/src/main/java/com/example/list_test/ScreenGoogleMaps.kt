package com.example.list_test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

@Composable fun MapsForListData(){


    Column() {
        //Top
        Box((
                androidx.compose.ui.Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(1.dp)
                    .background(Color.Gray)),
            contentAlignment = Alignment.Center
        ) {

        }
   //Mapa
    Box((
            androidx.compose.ui.Modifier
                .width(60.dp)
                .height(60.dp)
                .padding(1.dp)
                .background(Color.Gray)),
        contentAlignment = Alignment.Center
    ) {

    }
        //Data koncowa
    Box(
        androidx.compose.ui.Modifier
            .width(60.dp)
            .height(60.dp)
            .padding(1.dp)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {

    }
    }


}