package com.example.appEntregable.ui.navigation
import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appEntregable.examples.CalculadoraOps
import com.example.appEntregable.ui.screens.Basics
import com.example.appEntregable.ui.screens.FuncionContador
import com.example.appEntregable.ui.screens.Menu
import com.example.appEntregable.ui.screens.PantallaNavegacionVentana1
import com.example.appEntregable.ui.screens.ScaffoldPantallaNavegacion
import com.example.appEntregable.ui.screens.ScreenTablas
import com.example.appEntregable.ui.screens.TextoBox
import com.example.appEntregable.ui.screens.TextoColumnas
import com.example.appEntregable.ui.screens.TextosDos

import com.example.appEntregable.ui.screens.Ventana1funcion
import com.example.appEntregable.ui.screens.calculadora.CalculadoraSimple
import com.example.appEntregable.ui.screens.hiTeis
import com.example.appEntregable.ui.screens.ventana2

@Composable
fun Navigation(){

    val navController = rememberNavController()
    NavHost(navController, startDestination = Screens.Menu.route) {
        //pantalla principal con la navegación
        composable(route = Screens.Menu.route) { Menu(navController) }//Nombre del fichero .kt al que navegar

        composable(route = Screens.ColumnaTextoCentrado.route){
            TextoColumnas(navController)}

        composable(route = Screens.ColumnaTextoPorPesos.route){
            TextosDos(navController)
        }

        composable(route = Screens.BoxTexto.route){
            TextoBox(navController)
        }
        composable(route = Screens.BasicJetPack.route){
            Basics(navController)
        }
        composable(route = Screens.HolaTeis.route){
            hiTeis(navController)
        }

        composable(route = Screens.Contador.route){
            FuncionContador(navController)
        }

        composable(route = Screens.Scafold.route){
            ScaffoldPantallaNavegacion()
        }
        composable(route = Screens.ScafoldNav.route){
            ScreenTablas()
        }
        composable(route = Screens.Calculadora.route) {
            CalculadoraSimple(navController) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.CalculadoraOps.route) {
            CalculadoraOps(navController) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.Ventana1.route) {
            Ventana1funcion(navController) //Nombre de la función composable a la que navegar
        }

        composable(route = Screens.Ventana2.route) {
            ventana2(navController) //Nombre de la función composable a la que navegar
        }

    }
}