package com.example.appEntregable.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FuncionContador(navController: NavController){
    Contador(contar = cuenta(0))
}

class cuenta(contar: Int){
    var contar by mutableStateOf(contar)

}
@Composable
fun Contador(contar: cuenta){

    Box(modifier = Modifier.fillMaxSize()){
        Button(onClick = { if (contar.contar<10) contar.contar+=1 },
            enabled = if (contar.contar<10) true else false,
            modifier = Modifier.align(Alignment.Center),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.Black) )
        {
            Text(text = "Contar",
                style = TextStyle(fontSize = 30.sp)
            )
        }
        Text(text = contar.contar.toString(), modifier = Modifier
            .align(Alignment.Center)
            .padding(top = 100.dp))
    }
}