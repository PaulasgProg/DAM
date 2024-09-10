package com.tutorialesprogramacionya.compose14

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            PantallaNavegacion()
//        }
//    }
//}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenTablas() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tablas Multiplicar")
                }//,
            )
        }
    ){
        PantallaNavegacion()
    }
}

@Composable
fun PantallaNavegacion() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "pantalla1") {
        composable("pantalla1") {
            Pantalla1(navController)
        }
        composable("pantalla2") {
            Pantalla2(navController)
        }
        composable("pantalla3") {
            Pantalla3(navController)
        }
    }
}

@Composable
fun Pantalla1(navController: NavController) {
    Column() {
        BarraBotones(navController = navController)
        Text(text = "Tabla del 2")
        for (x in 1..10) {
            Text("2 * $x = ${x * 2}")
        }
    }
}

@Composable
fun Pantalla2(navController: NavController) {
    Column() {
        BarraBotones(navController = navController)
        Text(text = "Tabla del 5")
        for (x in 1..10) {
            Text("5 * $x = ${x * 5}")
        }
    }
}

@Composable
fun Pantalla3(navController: NavController) {
    Column() {
        BarraBotones(navController = navController)
        Text(text = "Tabla del 10")
        for (x in 1..10) {
            Text("5 * $x = ${x * 10}")
        }
    }
}

@Composable
fun BarraBotones(navController: NavController) {
    Row(horizontalArrangement = Arrangement.SpaceAround,modifier=Modifier.fillMaxWidth().padding(10.dp)) {
        Button(onClick = {
            navController.navigate("pantalla1")
        }) {
            Text("Tabla 2")
        }
        Button(onClick = {
            navController.navigate("pantalla2")
        }) {
            Text("Tabla 5")
        }
        Button(onClick = {
            navController.navigate("pantalla3")
        }) {
            Text("Tabla 10")
        }
    }

}