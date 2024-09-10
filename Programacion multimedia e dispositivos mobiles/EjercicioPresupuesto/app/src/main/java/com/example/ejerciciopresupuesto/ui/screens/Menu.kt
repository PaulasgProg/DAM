package com.example.ejerciciopresupuesto.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.ejerciciopresupuesto.ui.navigation.Screens


@Composable
fun Menu(navController: NavController){

    //Si por ejemplo fuese un listado de botones que permiten navegar:

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

    /*    Button(onClick = { navController.navigate(route = Screens.ColumnaTextoCentrado.route) }) {
            Text(text = "Ejemplo Textos Columna")
        }
        Button(onClick = { navController.navigate(route = Screens.ColumnaTextoPorPesos.route) }) {
            Text(text = "Ejemplo Textos por pesos")
        }
        Button(onClick = { navController.navigate(route = Screens.BoxTexto.route) }) {
            Text(text = "Ejemplo Textos en box")
        }
        Button(onClick = { navController.navigate(route = Screens.BasicJetPack.route) }) {
            Text(text = "Ejemplo básicos jetpack")
        }
        Button(onClick = { navController.navigate(route = Screens.HolaTeis.route) }) {
            Text(text = "Ejemplo teis")
        }
        Button(onClick = { navController.navigate(route = Screens.Contador.route) }) {
            Text(text = "Ejemplo contador")
        }

        Button(onClick = { navController.navigate(route = Screens.Scafold.route) }) {
            Text(text = "Ejemplo Scafold")
        }
        Button(onClick = { navController.navigate(route = Screens.ScafoldNav.route) }) {
            Text(text = "Ejemplo Scafold+Navigation")
        }

        Button(onClick = { navController.navigate(route = Screens.Calculadora.route) }) {
            Text(text = "Ejemplo Calculadora Simple")
        }

        Button(onClick = { navController.navigate(route = Screens.CalculadoraOps.route) }) {
            Text(text = "Ejemplo Calculadora Operaciones")
        }*/
        Button(onClick = { navController.navigate(route = Screens.Ventana1.route) }) {
            Text(text = "Listado presupuesto")
        }

        Button(onClick = { navController.navigate(route = Screens.Ventana2.route) }) {
            Text(text = "Agenda contactos v1")
        }

        Button(onClick = { navController.navigate(route = Screens.Ventana3.route) }) {
            Text(text = "Agenda contactos v2")
        }
        Button(onClick = { navController.navigate(route = Screens.Ventana4.route) }) {
            Text(text = "Login")
        }
        Button(onClick = { navController.navigate(route = Screens.Ventana5.route) }) {
            Text(text = "Lista Compra")
        }
    }
}
