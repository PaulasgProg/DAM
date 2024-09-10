package com.example.viewmodel.repaso.conlistado.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Element(val name:String/*,var checked:Boolean=false*/){//var indica que es prop de la clase

    var checked by mutableStateOf(false)//para no tener siempre iniciado a false el checked con el constructor
}

fun getFakeElements() = //Simula lo que ser recuperaría de CapaDatos ->debería ir en su propia carpeta Data
    List(30) { i -> Element( "Element $i") }//Lista de 30 eltos siguiendo indicación de lambda
//List(30) { Product( "Producto $it") }//otra forma