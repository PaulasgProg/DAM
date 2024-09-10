package com.example.composeprimeraslayouts_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class Ej04Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ColumnaDistribuyendoEspacio()
        }
    }


}


@Composable
fun ColumnaDistribuyendoEspacio(
    fila1: String = "Texto 1",
    fila2: String = "Texto 2",
    fila3: String = "Texto 3"
) {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly, // Distribuyendo espacio homogéneamente
        horizontalAlignment = Alignment.CenterHorizontally, // Alineación horizontal
    ) {
        Text(text = fila1, Modifier.background(Color.Red))
        Text(text = fila2, Modifier.background(Color.Green))
        Text(text = fila3, Modifier.background(Color.Cyan))

    }
}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreviewEj04() {
    ColumnaDistribuyendoEspacio()
}
