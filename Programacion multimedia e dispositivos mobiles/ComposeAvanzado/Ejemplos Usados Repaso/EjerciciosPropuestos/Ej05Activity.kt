package com.example.composeprimeraslayouts_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class Ej05Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ColumnaDistribuidaPorPesos()
        }
    }


}


@Composable
fun ColumnaDistribuidaPorPesos(
    fila1: String = "Texto 1", fila2: String = "Texto 2", fila3: String = "Texto 3"
) {
    Column(
        modifier = Modifier
            //.fillMaxSize()
            .background(Color.LightGray),
        // quitamos el arrangement para usar pesos
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {         // (1)
        Text(
            text = fila1,
            Modifier
                .background(Color.Red)
                .weight(1f)
            //.weight(0.25f) // Alternativa con porcentajes (25% o 1/4 de pantalla)
        )
        Text(
            text = fila2,
            Modifier
                .background(Color.Yellow)
                .fillMaxSize()
                .weight(1f)
            //.weight(0.25f)
        )
        Text(
            text = fila3,
            Modifier
                .background(Color.Green)
                .weight(2f)
            //.weight(0.5f)
        )

    }
}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreviewEj05() {
    ColumnaDistribuidaPorPesos()
}


/* (1) Distribución de filas por pesos. En este caso la fila 3 (peso 2) ocupa el doble de
la otras 2 (peso 1). Por tanto, las 2 primeras ocupan cada una 1/4 de pantalla (o un 25%) y
la otra media pantalla (50%).
 */