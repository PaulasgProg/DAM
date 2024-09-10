package com.example.ejerciciopresupuesto.ui.screens

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class User(val username: String, val password: String)

val users = listOf(
    User("user1", "password1"),
    User("user2", "password2"),
    User("user3", "password3")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var remainingAttempts by remember { mutableStateOf(3) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Button(
            onClick = {
                val user = users.find { it.username == username && it.password == password }
                if (user != null) {
                    onLoginSuccess()
                } else {
                    remainingAttempts -= 1
                    if (remainingAttempts == 0) {
                        remainingAttempts = 3
                        errorMessage = "Has excedido el número máximo de intentos."
                    } else {
                        errorMessage = "Usuario o contraseña incorrectos. Intentos restantes: $remainingAttempts"
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Iniciar sesión")
        }

        errorMessage?.let {
            Text(it, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.error)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var loggedIn by remember { mutableStateOf(false) }

    if (loggedIn) {
        // Muestra la pantalla principal del sistema después del inicio de sesión exitoso
        // Esta puede ser una pantalla de inicio o cualquier otra pantalla deseada
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Sistema de pruebas") },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
                    )
            }
        ) {
            Text(text = "$it", modifier = Modifier.background(Color.Green))
        }
    } else {
        // Muestra la pantalla de inicio de sesión si el usuario no ha iniciado sesión
        LoginScreen {
            loggedIn = true
        }
    }
}

@Composable
fun LoginApp(navController: NavController) {
    // Muestra la pantalla principal de la aplicación
    MainScreen()
}

