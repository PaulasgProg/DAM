package com.example.appEntregable.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun TextosDos(navController: NavController){
    textos()
}

@Preview(showBackground = true)
@Composable
fun textos(){
    //por pesos
    Column(modifier = Modifier
        .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Texto1", modifier = Modifier
            .background(Color.Red)
            .weight(1f))
        Text(text = "Texto2", modifier = Modifier
            .background(Color.Yellow)
            .weight(1f)
            .fillMaxSize())
        Text(text = "Texto3",modifier = Modifier
            .background(Color.Green)
            .weight(2f) )
    }
}