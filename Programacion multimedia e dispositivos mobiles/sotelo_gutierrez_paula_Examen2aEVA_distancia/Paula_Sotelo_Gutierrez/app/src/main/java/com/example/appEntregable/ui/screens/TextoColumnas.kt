package com.example.appEntregable.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
fun TextoColumnas(navController: NavController){
    graficos()
}

@Preview(showBackground = true)
@Composable
fun graficos(){
    Column (modifier = Modifier.fillMaxSize().background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly){

        Text(text = "Texto1", modifier = Modifier.background(Color.Red))
        Text(text = "Texto2", modifier = Modifier.background(Color.Green))
        Text(text = "Texto3", modifier = Modifier.background(Color.Cyan))

    }
}