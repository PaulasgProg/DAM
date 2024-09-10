package com.example.compose_calculadora_navigation.examples

import android.widget.RadioGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_calculadora_navigation.ui.navigation.Screens
import com.example.compose_calculadora_navigation.ui.screens.calculadora.Spacer

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
//@Composable
//fun Spacer() {
//    androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(16.dp))
//}
