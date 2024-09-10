package com.example.composeprimeraslayouts_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeprimeraslayouts_example.R

class Ej03Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TextosEnCaja(saludarA = stringResource(R.string.jetpack_compose))
        }
    }
}

@Composable
fun TextosEnCaja(saludo: String = stringResource(R.string.hola_coma), saludarA: String) {
    Box(
        Modifier
            .background(Color.Yellow)
            .fillMaxSize()
            .padding(50.dp)
            .background(Color.Cyan)
    ) {
        Text(text = saludo)
        Text(text = saludarA, Modifier.align(Alignment.CenterStart))
        Text(text = "Otro texto hardcodeado", Modifier.align(Alignment.BottomEnd))
    }
}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreviewEj03() {
    TextosEnCaja(saludarA = stringResource(R.string.jetpack_compose))
}
