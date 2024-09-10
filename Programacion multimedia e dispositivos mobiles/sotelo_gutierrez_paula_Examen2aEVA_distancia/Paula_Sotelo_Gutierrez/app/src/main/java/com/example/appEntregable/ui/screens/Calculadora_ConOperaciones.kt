package com.example.appEntregable.examples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@Composable
fun CalculadoraOps(navController: NavController){
    val viewModel: CalculadoraViewModel = viewModel()
    ContenedorCalculadora(viewModel)
}


// Define un nuevo tipo de datos que represente el estado necesario para la calculadora

data class CalculadoraState(
    var firstNumber :String="",
    var secondNumber :String="",
    var resultado: String = "",
    var colorResultado: Color = Color.Black,
    var selectedOption: String = "Suma" // Valor predeterminado para la opción seleccionada
)

@Composable
fun Calculadora(modifier: Modifier = Modifier, state: CalculadoraState, onStateChanged: (CalculadoraState) -> Unit){
    val options = listOf("Suma", "Resta", "Multiplicacion", "Division")

    Box (modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp)){

        Column(modifier=Modifier.align(Alignment.TopCenter)) {
            TextField(
                value = state.firstNumber,
                onValueChange = { onStateChanged(state.copy(firstNumber = it)) },
                label = { androidx.compose.material3.Text("Número 1") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(modifier = Modifier
                .padding(top = 20.dp),
                value = state.secondNumber,
                onValueChange = { onStateChanged(state.copy(secondNumber = it)) },
                label = { androidx.compose.material3.Text("Número 2") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Muestra las opciones como botones de radio
            options.forEach { text ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    androidx.compose.material3.RadioButton(
                        selected = state.selectedOption == text,
                        onClick = { onStateChanged(state.copy(selectedOption = text)) }
                    )
                    androidx.compose.material3.Text(
                        text = text

                    )
                }
            }

        }

        androidx.compose.material3.Text(text = state.resultado,
            modifier = Modifier.padding(top = 200.dp).align(Alignment.Center),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp // Cambia este valor según el tamaño deseado
            ),
            color = state.colorResultado
        )


        androidx.compose.material3.Button(onClick = {
            // Realiza el cálculo
            val result = when (state.selectedOption) {
                "Suma" -> (state.firstNumber.toInt() + state.secondNumber.toInt()).toString()
                "Resta" -> (state.firstNumber.toInt() - state.secondNumber.toInt()).toString()
                "Multiplicacion" -> (state.firstNumber.toInt() * state.secondNumber.toInt()).toString()
                "Division" -> (state.firstNumber.toInt() / state.secondNumber.toInt()).toString()
                else -> ""
            }

            // Actualiza el estado con el resultado y el color
            onStateChanged(
                state.copy(
                    resultado = result,
                    colorResultado =  when {
                        result.toInt() < 25 -> Color.Cyan
                        result.toInt() > 25 -> Color.Blue
                        result.toInt() == 25 -> Color.Red
                        else -> Color.Black
                    }
                )
            )
        },
            modifier = Modifier.padding(top = 150.dp,).align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta, contentColor = Color.Black)) {

            androidx.compose.material3.Text(text = "Calcular")
        }
    }
}
class CalculadoraViewModel : ViewModel() {
    private val _state = mutableStateOf(CalculadoraState())
    val state: State<CalculadoraState> = _state

    fun updateState(newState: CalculadoraState) {
        _state.value = newState
    }
}

@Composable
fun ContenedorCalculadora(viewModel: CalculadoraViewModel) {
    val calculadoraState by viewModel.state

    Calculadora(
        state = calculadoraState,
        onStateChanged = { newState -> viewModel.updateState(newState) }
    )
}




/*
@Preview(showSystemUi = true)
@Composable
fun CalculadoraOps(){

    var op1 by remember { mutableStateOf("") }
    var op2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf(0.0) }
    var sumColor by remember { mutableStateOf(Color.Black) }
    var operation by remember { mutableStateOf(0) }

    val operationTypes = listOf("Suma", "Resta", "Multiplicación", "División")
    val currentSelection = remember { mutableStateOf(operationTypes.first()) }

    val onCalcular = {
        //cálculo operación
        operation = when{
            currentSelection.value.contains("Resta")-> 1
            currentSelection.value.contains("Multiplicación")->2
            currentSelection.value.contains("División")->3
            else -> 0
        }
        //cálculoResultadoOperación
        when {
            operation == 1 ->resultado=op1.toDouble()-op2.toDouble()
            operation == 2 ->resultado=op1.toDouble()*op2.toDouble()
            operation == 3 ->resultado=op1.toDouble()/op2.toDouble()
            else -> resultado=op1.toDouble()+op2.toDouble()
        }

        //calculoColor
        sumColor = when {
            resultado < 25.0 -> Color.Cyan
            resultado > 25.0 -> Color.Blue
            resultado == 25.0 -> Color.Red
            else -> Color.Black
        }
    }

    Column(
        Modifier.fillMaxSize()
                .padding(vertical = 64.dp, horizontal = 16.dp),
        horizontalAlignment=Alignment.CenterHorizontally
    ){
        TextField(
            value = op1,
            onValueChange = { op1 = it },
            label = { Text("Operando 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer()

        TextField(
            value = op2,
            onValueChange = { op2 = it },
            label = { Text("Operando 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer()


//        RadioGroup(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth(),
//            items = operationTypes,
//            selection = currentSelection.value,
//            onItemClick = { clickedItem ->
//                currentSelection.value = clickedItem
//
//            }
//        )

        Spacer()

        Text(
            text="Resultado= $resultado " ,
                    //y $operation",
            fontSize = 25.sp,
            //color
            color=sumColor
        )

        Spacer()

        Button(onClick = onCalcular
//                {
//                    //cálculo operación
//                    operation = when{
//                        currentSelection.value.contains("Resta")-> 1
//                        currentSelection.value.contains("Multiplicación")->2
//                        currentSelection.value.contains("División")->3
//                        else -> 0
//                    }
//                 //cálculoResultadoOperación
//                    when {
//                        operation == 1 ->resultado=op1.toDouble()-op2.toDouble()
//                        operation == 2 ->resultado=op1.toDouble()*op2.toDouble()
//                        operation == 3 ->resultado=op1.toDouble()/op2.toDouble()
//                        else -> resultado=op1.toDouble()+op2.toDouble()
//                    }
//
//                 //calculoColor
//                     sumColor = when {
//                        resultado < 25.0 -> Color.Cyan
//                        resultado > 25.0 -> Color.Blue
//                        resultado == 25.0 -> Color.Red
//                        else -> Color.Black
//                    }

//                }
        ){
            Text("Calcular")
        }
    }

}

*/
//@Composable
//fun Spacer() {
//    androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(16.dp))
//}