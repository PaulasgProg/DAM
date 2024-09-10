package com.example.composeprimeraslayouts_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeprimeraslayouts_example.R

class Ej02cActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DosTextosEnColumna(saludarA = getString(R.string.jetpack_compose))
        }
    }
}

@Composable
fun DosTextosEnColumna(
    saludo: String = stringResource(R.string.hola_coma),  // (1) (2)
    saludarA: String,
) {
    Row(
        modifier = Modifier
            .fillMaxSize() // Para ocupar toda la pantalla
            .background(Color.Cyan)
            .padding(30.dp),   // El orden en que concatenamos los modificadores es relevante
        verticalAlignment = Alignment.CenterVertically, // (3)
    ) {
        /* El atributo content, que recibe una lambda, se debe sacar de los paréntesis */
        Text(text = saludo)
        Text(text = "Texto hardcodeado")
        Text(text = saludarA,
            modifier = Modifier
                .background(Color.Magenta)
                .align(Top))  // (4)
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreviewEj02() {
    DosTextosEnColumna(saludarA = stringResource(R.string.jetpack_compose))  // (2)
}


/*

(1)
Kotlin permite definir argumentos por defecto. En este caso la función DosTextosEnColumna puede
recibir dos parámetros o solo el segundo, tomando el primero con su valor por defecto.
También podría definirse el mismo comportamiento sobrecargando, pero así se hace en menos líneas.

(2)
Compose usa el método stringResource para recuperar recursos de strings.xml fuera de un Context.

(3)
Indicamos la alineación horizontal que tendrán los elementos de la columna. Es un parámentro de
Column porque afecta a su contenido. No tendría sentido una alineación vertical, ya que eso
vendrá indicado por la propia naturaleza de la columna (apila los elementos verticalmente).

(4)
Sin embargo, siempre tendrá más prioridad el modificador de cada elemento. Al estar dentro de
ColumnScope, este Text puede usar la función de extensión
(https://kotlinlang.org/docs/extensions.html) align, que solo puede recibir una alineación
horizontal. No tendría sentido una vertical.

*/
