package com.example.composebotones

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebotones.R

// Modelo de datos
data class Item(val text: String)

// Función que muestra una lista de elementos utilizando LazyColumn
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
//fun LazyColumnExample(items: List<Item>) {
fun LazyColumnExample(items: List<String>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.repaso)) })
        }
    ) {
        LazyColumn {
            items(items) { item ->
//            ListItem(item = item
                ListItem(item = item)
            }
        }
    }
}

// Composable que representa un elemento de la lista
@Composable
//fun ListItem(item: Item,modifier: Modifier = Modifier) {
fun ListItem(item: String, modifier: Modifier = Modifier) {
    // Aquí puedes personalizar el diseño de cada elemento de la lista
    var counter by remember {mutableStateOf("")}
    var counterNum by remember {mutableStateOf(0)}
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item)

        IconButton(onClick = {
            counter=counter+"+1"
            counterNum++
        }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increment"
            )
        }

        IconButton(onClick = {
            counter=counter+"-1"
            counterNum--
                             },
            enabled = counterNum > 0) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Decrement"
            )
        }

        Column(){
            Text(text = "Clicks: $counter")
            Text(text = "ClicksNum: $counterNum")
        }
    }

}

// Ejemplo de uso
@Preview(showSystemUi = true)
@Composable
fun EjemploLazyColumn() {
//    val items = listOf(
//        Item("Elemento 1"),
//        Item("Elemento 2"),
//        Item("Elemento 3"),
//        // Agrega más elementos si lo deseas
//    )

    val items = List(10){ "Elto-${it+1}" }

    LazyColumnExample(items = items)
}

