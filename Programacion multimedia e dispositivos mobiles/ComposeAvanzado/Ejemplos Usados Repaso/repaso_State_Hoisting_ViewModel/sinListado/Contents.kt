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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun EjA_VM_Content(name: String, onNameChange: (String) -> Unit,
                   count: Int, onCountChange: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Row(verticalAlignment= Alignment.CenterVertically){
//            Button(onClick= onCountChange) {
            Button(onClick= {onCountChange()}) {
                Text(text="Contar")
            }
            Text(text=count.toString())
        }

        OutlinedTextField(value = name,
            onValueChange = onNameChange,
            label = { Text(text = "Name") }
        )

        TextField(value = name,
            onValueChange = onNameChange,
            label = { Text(text = "Name") }
        )

//        if (name.isNotBlank())
        Text(text = "¡Hola, $name!", Modifier.padding(10.dp))
    }
}

@Composable
fun EjD_VM_Content(name: String, onNameChange: (String) -> Unit,
                   count: Int, onCountChange: () -> Unit) {
    EjA_VM_Content(name,onNameChange,count, onCountChange)
}

