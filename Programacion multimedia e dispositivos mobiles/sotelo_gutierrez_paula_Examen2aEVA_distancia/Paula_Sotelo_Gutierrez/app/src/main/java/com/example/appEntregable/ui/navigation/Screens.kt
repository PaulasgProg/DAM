package com.example.appEntregable.ui.navigation

sealed class Screens(val route: String){
    object Menu: Screens("initial_screen")//info a aparecer en pantalla

    object ColumnaTextoCentrado: Screens("Textos en columna")
    object ColumnaTextoPorPesos: Screens("Textos en columna por pesos")
    object BoxTexto: Screens("Textos en box")
    object BasicJetPack: Screens("Básicos JETPACK")
    object HolaTeis: Screens("Ejercicio Teis")
    object Contador: Screens("Ejercicio Contador")
    object Scafold: Screens("Ejercicio Scafold")
    object ScafoldNav: Screens("Ejercicio Scafold+Navigation")
    object Calculadora: Screens("ejCalculadora")
    object CalculadoraOps: Screens("ejCalculadoraOperaciones")
    object Ventana1: Screens("ejercicio ventana1")
    object Ventana2: Screens("ej1")


}

//Aquellos ficheros que correspondan a un menú, deberán tener la estructura.