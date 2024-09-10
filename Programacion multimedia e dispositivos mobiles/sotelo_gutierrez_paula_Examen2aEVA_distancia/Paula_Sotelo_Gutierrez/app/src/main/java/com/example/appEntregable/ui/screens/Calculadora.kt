package com.example.appEntregable.ui.screens.calculadora

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController


@Composable
fun CalculadoraSimple(navController: NavController){
    ContenedorCalculadoraSimple(viewModel = CalculadoraViewModelSimple())
}

class CalculadoraViewModelSimple : ViewModel() {
    private val _state = mutableStateOf(CalculadoraStateSimple())
    val state: State<CalculadoraStateSimple> = _state

    fun updateState(newState: CalculadoraStateSimple) {
        _state.value = newState
    }
}

@Composable
fun ContenedorCalculadoraSimple(viewModel: CalculadoraViewModelSimple) {
    val calculadoraStateSimple by viewModel.state

    CalculadoraSimple(
        state = calculadoraStateSimple,
        onStateChanged = { newState -> viewModel.updateState(newState) }
    )
}
data class CalculadoraStateSimple(
    var firstNumber :String="",
    var secondNumber :String="",
    var resultado: Double = 0.0,
    var colorResultado: Color = Color.Black,
    var selectedOption: String = "Suma" // Valor predeterminado para la opción seleccionada
)


@Composable
fun CalculadoraSimple(state: CalculadoraStateSimple, onStateChanged: (CalculadoraStateSimple) -> Unit){


    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 16.dp),
        horizontalAlignment=Alignment.CenterHorizontally
    ){
        TextField(
            value = state.firstNumber,
            onValueChange = { onStateChanged(state.copy(firstNumber = it)) },
            label = { Text("Operando 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer()

        TextField(
            value = state.secondNumber,
            onValueChange = { onStateChanged(state.copy(secondNumber = it)) },
            label = { Text("Operando 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer()

        Text(
            text="Suma= ${state.resultado}",
            fontSize = 25.sp
            //color
        )

        Spacer()

        Button(onClick = //onCalcular
                {state.resultado= (state.firstNumber.toDouble()+state.secondNumber.toDouble())
                 //calculoColor
                    onStateChanged(
                        state.copy(
                            resultado = state.resultado,
                            colorResultado =  when {
                                state.resultado.toInt() < 25 -> Color.Cyan
                                state.resultado.toInt() > 25 -> Color.Blue
                                state.resultado.toInt() == 25 -> Color.Red
                                else -> Color.Black
                            }
                        )
                    )
                },// Actualiza el estado con el resultado y el color

        ){
            Text("Calcular")
        }
    }

}

@Composable
fun Spacer() {
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(16.dp))
}

