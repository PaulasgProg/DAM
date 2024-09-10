package com.example.ejerciciopresupuesto.ui.navigation
import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejerciciopresupuesto.ui.screens.LoginApp
import com.example.ejerciciopresupuesto.ui.screens.Menu
import com.example.ejerciciopresupuesto.ui.screens.ventana2
import com.example.ejerciciopresupuesto.ui.screens.Ventana1funcion
import com.example.ejerciciopresupuesto.ui.screens.agendaContactos
import com.example.ejerciciopresupuesto.ui.screens.agendaContactosv2
import com.example.ejerciciopresupuesto.ui.screens.lista


@Composable
fun Navigation(){

    val navController = rememberNavController()
    NavHost(navController, startDestination = Screens.Menu.route) {
        //pantalla principal con la navegación
        composable(route = Screens.Menu.route) { Menu(navController) }
        composable(route = Screens.Ventana1.route) {
            Ventana1funcion(navController) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.Ventana2.route) {
            agendaContactos(navController) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.Ventana3.route) {
            agendaContactosv2(navController) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.Ventana4.route) {
            LoginApp(navController) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.Ventana5.route) {
            lista(navController) //Nombre de la función composable a la que navegar
        }



    }
}


