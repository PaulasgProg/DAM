package com.example.appEntregable.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appEntregable.R

@Composable
fun ListaImagenes(navController: NavController){
   // val imageList = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
    //ImageList(imageList = imageList)
}


@Composable
fun ImageList(imageList: List<Int>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(imageList) { imageResource ->
            ImageListItem(imageResource = imageResource)
        }
    }
}

@Composable
fun ImageListItem(imageResource: Int) {
    val painter: Painter = painterResource(id = imageResource)
    Image(
        painter = painter,
        contentDescription = null, // Ajusta según sea necesario
        modifier = Modifier
            .height(200.dp) // Ajusta la altura de la imagen
            .fillMaxWidth()
    )
}