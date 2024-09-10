package com.example.ejerciciopresupuesto.ui.navigation

sealed class Screens(val route: String){
    object Menu: Screens("initial_screen")//info a aparecer en pantalla

    object Ventana1: Screens("ListadoPresupuesto")
    object Ventana2: Screens("AgendaContactos")
    object Ventana3: Screens("AgendaContactosv2")
    object Ventana4: Screens("Login")
    object Ventana5: Screens("ListaCompra")


}

//Aquellos ficheros que correspondan a un menú, deberán tener la estructura.