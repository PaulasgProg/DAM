package com.example.a3aeva_listados.explicacion_statehoisting_udf_viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {//al usar VM, ya no se necesita remember
    private var _numero by mutableStateOf(0)
    val numero get() = _numero

    private var _nombre by mutableStateOf("")
    val nombre get() = _nombre

    fun incNumber() {
        _numero++
    }

    fun changeName(name:String) {
        _nombre=name
    }
}