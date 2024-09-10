package com.example.appEntregable.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TextoBox(navController: NavController){
    textobox()
    
}


@Preview(showBackground = true)
@Composable
fun textobox(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)
        .padding(50.dp)
        .background(Color.Cyan)){
        Text(text = "Hola")
        Text(text = "JetPack Compose", modifier = Modifier.align(Alignment.CenterStart))
        Text(text = "Otro texto", modifier = Modifier.align(Alignment.BottomEnd))
    }
}