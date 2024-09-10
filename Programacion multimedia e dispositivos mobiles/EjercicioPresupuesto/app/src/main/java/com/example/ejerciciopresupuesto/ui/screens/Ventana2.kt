package com.example.ejerciciopresupuesto.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.io.File


data class Contacto(
    val id: Int,
    var name: String,
    var phoneNumber: String,
    var email: String
)
val listaContactos = mutableListOf(
    Contacto(1, "Juan Pérez", "123456789", "juan.perez@example.com"),
    Contacto(2, "María Gómez", "987654321", "maria.gomez@example.com")
)
fun addContacto(contacto: Contacto) {
    contactosState = contactosState + contacto
}
fun getContactos(): List<Contacto> {
    return listaContactos
}
fun deleteContacto(contacto: Contacto) {
    contactosState = contactosState - contacto
}
fun saveContactos(context: Context) {
    val file = File(context.filesDir, "contactos.txt")
    file.printWriter().use { out ->
        listaContactos.forEach {
            out.println("${it.id},${it.name},${it.phoneNumber},${it.email}")
        }
    }
}
fun loadContactos(context: Context) {
    val file = File(context.filesDir, "contactos.txt")
    if (file.exists()) {
        file.forEachLine {
            val parts = it.split(",")
            if (parts.size == 4) {
                val contacto = Contacto(parts[0].toInt(), parts[1], parts[2], parts[3])
                listaContactos.add(contacto)
            }
        }
    }
}
var contactosState by mutableStateOf(listOf<Contacto>())

fun updateContacto(updatedContacto: Contacto) {
    contactosState = contactosState.map { if (it.id == updatedContacto.id) updatedContacto else it }
}
@Composable
fun ContactListScreen() {
    val context = LocalContext.current

    // Load contactos initially
    if (contactosState.isEmpty()) {
        contactosState = getContactos()
    }

    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn {
            items(contactosState) { contacto ->
                ContactItem(contacto, onDelete = {
                    deleteContacto(contacto)
                    saveContactos(context)
                }, onUpdate = { updatedContacto ->
                    updateContacto(updatedContacto)
                    saveContactos(context)
                })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Número de Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (name.isNotEmpty() && phoneNumber.isNotEmpty() && email.isNotEmpty()) {
                val newContacto = Contacto(contactosState.size + 1, name, phoneNumber, email)
                addContacto(newContacto)
                saveContactos(context)

                name = ""
                phoneNumber = ""
                email = ""
            }
        }) {
            Text("Agregar Contacto")
        }
    }
}
@Composable
fun ContactItem(contacto: Contacto, onDelete: (Contacto) -> Unit, onUpdate: (Contacto) -> Unit) {
    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(contacto.name) }
    var phoneNumber by remember { mutableStateOf(contacto.phoneNumber) }
    var email by remember { mutableStateOf(contacto.email) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, androidx.compose.ui.graphics.Color.Gray)
            .padding(8.dp)
    ) {
        if (isEditing) {
            Column(modifier = Modifier.weight(1f)) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Número de Teléfono") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Correo Electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            IconButton(onClick = {
                val updatedContacto = contacto.copy(name = name, phoneNumber = phoneNumber, email = email)
                onUpdate(updatedContacto)
                isEditing = false
            }) {
                Icon(Icons.Default.Check, contentDescription = "Guardar")
            }
        } else {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = contacto.name, style = MaterialTheme.typography.titleLarge)
                Text(text = contacto.phoneNumber, style = MaterialTheme.typography.bodyMedium)
                Text(text = contacto.email, style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = { isEditing = true }) {
                Icon(Icons.Default.Edit, contentDescription = "Editar")
            }
        }
        IconButton(onClick = { onDelete(contacto) }) {
            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
        }
    }
}


@Composable
fun agendaContactos(navController: NavController){
    ContactListScreen()
}

@Composable
fun ventana2(navController: NavController){
    val viewModel: ExamenViewModel = viewModel()
    ContenedorPlantilla(viewModel =viewModel )
}

data class ContadorEx2(
    var contar: Int=0,
    var iniciar:String="Sin iniciar")


class ExamenViewModel : ViewModel() {
    private val _state = mutableStateOf(ContadorEx2())
    val state: State<ContadorEx2> = _state

    fun updateState(newState: ContadorEx2) {
        _state.value = newState
    }
}

@Composable
fun ContenedorPlantilla(viewModel: ExamenViewModel) {
    val contadorState by viewModel.state

    PantallaNavegacionVentana2(
        contador = contadorState,
        onStateChanged = { newState -> viewModel.updateState(newState) }
    )
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PantallaNavegacionVentana2( modifier: Modifier = Modifier, contador: ContadorEx2, onStateChanged: (ContadorEx2) -> Unit) {

    var cont by rememberSaveable { mutableStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Exam PMDP_D 2aEVA")
                },
                actions = {
                    IconButton(onClick = { cont=0;
                        onStateChanged(contador.copy(contar =cont, iniciar =if (cont>0) "Inicializado" else "Sin inicializar" )) }) {
                        Icon(imageVector = Icons.Default.Refresh , contentDescription =null )
                    }
                    IconButton(onClick = { cont++;
                        onStateChanged(contador.copy(contar =cont, iniciar =if (cont>0) "Inicializado" else "Sin inicializar" ))
                        }) {
                        Icon(imageVector = Icons.Default.Add , contentDescription = null)
                    }
                    IconButton(onClick = { cont--;
                        onStateChanged(contador.copy(contar =cont, iniciar =if (cont>0) "Inicializado" else "Sin inicializar" ))
                        }) {
                        Icon(imageVector = Icons.Default.Delete , contentDescription =null )
                    }

                }
            )
        }
    ) {

        Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Listado ${contador.iniciar}. Contador ${contador.contar}")

            Row {
                IconButton(onClick = {cont=0;
                    onStateChanged(contador.copy(contar =cont, iniciar =if (cont>0) "Inicializado" else "Sin inicializar" )) }) {
                    Icon(imageVector = Icons.Default.Refresh , contentDescription =null )

                }

                IconButton(onClick = { cont++;
                    onStateChanged(contador.copy(contar =cont, iniciar =if (cont>0) "Inicializado" else "Sin inicializar" ))}) {
                    Icon(imageVector = Icons.Default.Add , contentDescription = null)

                }

                IconButton(onClick = { cont--;
                    onStateChanged(contador.copy(contar =cont, iniciar =if (cont>0) "Inicializado" else "Sin inicializar" ))}) {
                    Icon(imageVector = Icons.Default.Delete , contentDescription =null )

                }
            }

            GreetingsEx2(modifier = Modifier.fillMaxWidth(), contador =contador)

        }

    }
}

@Composable
fun MyApp2(modifier: Modifier = Modifier, contador: ContadorEx2) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(contador) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {

        GreetingsEx2(contador =shouldShowOnboarding)
    }
}



@Composable
private fun GreetingsEx2(
    modifier: Modifier = Modifier,
    contador: ContadorEx2
) {
    val names: List<String> = List(contador.contar) {"$it"}
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            CardContent(name = name)
        }
    }
}

/*
@Composable
private fun Greeting1(modifier: Modifier = Modifier, name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}*/


@Composable
private fun CardContent(name: String) {
    var likes by rememberSaveable { mutableStateOf(0) }
    var favs by rememberSaveable { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {


        Text(text = "$name", modifier = Modifier.padding(start = 40.dp, end = 30.dp))
        IconButton(onClick = { likes++ }, modifier = Modifier.padding(end = 30.dp)) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null )
        }
        IconButton(onClick = { likes-- }, enabled = if(likes>0) true else false, modifier = Modifier.padding(end = 30.dp)) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null )
        }
        IconButton(onClick = { favs++ }, enabled = if(likes>0) true else false, modifier = Modifier.padding(end = 30.dp)) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = null )

        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Likes->$likes")
            Text(text = "Favs->$favs")
        }


    }
}

