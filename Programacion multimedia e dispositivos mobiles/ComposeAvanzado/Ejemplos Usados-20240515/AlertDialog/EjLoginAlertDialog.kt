package com.tutorialesprogramacionya.compose13

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            Login()
//        }
//    }
//}
@Preview(showSystemUi = true)
@Composable
fun LoginScreen() {
    Login()
}

@Composable
fun Login() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        var usuario by remember { mutableStateOf("") }
        var clave by remember { mutableStateOf("") }
//        var resultado by remember { mutableStateOf("Sin resultado") }
        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = {
                Text("Nombre de usuario")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = {
                Text("Clave")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        var muestra by remember { mutableStateOf(false) }
        if (muestra)
            DialogoAlerta(
                titulo="Cuidado",
                descripcion = "Borra el nombre de usuario y la clave ingresada?",
                respuesta = {
                    if (it) {
                        usuario = ""
                        clave = ""
                    }
                    muestra = false
                })
        Row() {
            Button(
                onClick = {
                    muestra = true
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Borrar datos")
            }
            Button(
                onClick = {
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Confirmar")
            }
        }

    }
}

@Composable
fun DialogoAlerta(titulo: String, descripcion: String, respuesta: (Boolean) -> Unit) {
    Column {
        var dialogoVisible by remember { mutableStateOf(true) }
        if (dialogoVisible) {
            AlertDialog(
                onDismissRequest = {
                    dialogoVisible=false
                    respuesta(false)
                },
                title = {
                    Text(text = "$titulo")
                },
                text = {
                    Text("$descripcion")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            dialogoVisible = false
                            respuesta(true)
                        }) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            dialogoVisible = false
                            respuesta(false)
                        }) {
                        Text("Cancelar")
                    }
                }
            )
        }

    }
}