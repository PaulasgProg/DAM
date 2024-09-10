package com.example.compose_calculadora_navigation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.compose_calculadora_navigation.ui.navigation.Screens

@Composable
fun Menu(navController: NavController){

    //Si por ejemplo fuese un listado de botones que permiten navegar:

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Button(onClick = { navController.navigate(route = Screens.Calculadora.route) }) {
            Text(text = "Ejemplo Calculadora Simple")
        }

        Button(onClick = { navController.navigate(route = Screens.CalculadoraOps.route) }) {
            Text(text = "Ejemplo Calculadora Operaciones")
        }

    }
}
