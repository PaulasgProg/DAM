package com.example.viewmodel.otromain

data class LibroState(
    //1.
    //val libreria: List<Libro> = emptyList(),//siempre poner valores por defecto a los estados

    //2.para que se pueda cambiar su contenido
    //val libreria: MutableList<Libro> = mutableListOf(),

    //pero sigue siendo erróneo que se pueda cambiar el listado->vuelta a 1.
    val libreria: List<Libro> = listOf(),
    val estaCargando:Boolean = false
)
