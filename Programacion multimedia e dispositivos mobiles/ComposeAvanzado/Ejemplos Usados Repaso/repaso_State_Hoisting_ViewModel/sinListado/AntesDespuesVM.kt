package com.example.a3aeva_listados.explicacion_statehoisting_udf_viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


//class ViewModel : ViewModel() {//al usar VM, ya no se necesita remember
//    private var _numero by mutableStateOf(0)
//    val numero get() = _numero
//
//    private var _nombre by mutableStateOf("")
//    val nombre get() = _nombre
//
//    fun incNumber() {
//        _numero++
//    }
//
//    fun changeName(name:String) {
//        _nombre=name
//    }
//}

@Preview
@Composable
fun EjSinStateHoisting_Content() {
    var text by remember { mutableStateOf("") }
    var count by remember { mutableStateOf(0) }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Row(verticalAlignment= Alignment.CenterVertically){
            Button(onClick= {count++}) {
                Text(text="Contar")
            }
            Text(text=count.toString())
        }

        OutlinedTextField(value = text,
            onValueChange = {text = it},
            label = { Text(text = "Name") }
        )

        TextField(value = text,
            onValueChange = {text = it},
            label = { Text(text = "Name") }
        )

//        if (name.isNotBlank())
        Text(text = "¡Hola, $text!", Modifier.padding(10.dp))
    }
}


@Preview
@Composable
fun Ej_SH_Before_VM() {  // Usando State Hoisting
    var text by remember { mutableStateOf("") }
    var count by remember { mutableStateOf(0) }

//    fun incrementa(){count++}//opcion1

//    EjA_VM_Content(name = text, onNameChange = { text = it },//UDF(StateHoisting)->Observer Pattern
//                   count = count, onCountChange= { count++})//opcion2-directamente la acción a realizar
////                    onCountChange= { incrementa()}//opcion1

    //opcion2
        EjA_VM_Content(text, { text = it },//escribiendo mucho menos código
                       count, { count++})

}

@Preview
@Composable
fun Ej_SH_After_VM() {

    val viewModel: ViewModel = viewModel()

    EjD_VM_Content( viewModel.nombre,{ viewModel.changeName(it) },
        viewModel.numero, { viewModel.incNumber() })
}

