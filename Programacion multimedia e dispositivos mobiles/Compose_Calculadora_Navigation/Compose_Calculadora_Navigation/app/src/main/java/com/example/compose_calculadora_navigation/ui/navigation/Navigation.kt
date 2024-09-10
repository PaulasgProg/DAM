package com.example.compose_calculadora_navigation.ui.navigation
import androidx.compose.runtime.Composable

import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_calculadora_navigation.examples.CalculadoraOps
import com.example.compose_calculadora_navigation.ui.screens.Menu
import com.example.compose_calculadora_navigation.ui.screens.calculadora.Calculadora

@Composable
fun Navigation(){

    val navController = rememberNavController()
    NavHost(navController, startDestination = Screens.Menu.route) {
        //pantalla principal con la navegación
        composable(route = Screens.Menu.route) { Menu(navController) }//Nombre del fichero .kt al que navegar
        composable(route = Screens.Calculadora.route) {
            Calculadora() //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.CalculadoraOps.route) {
            CalculadoraOps() //Nombre de la función composable a la que navegar
        }

    }
}