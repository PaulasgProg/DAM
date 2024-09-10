package com.example.ejerciciopresupuesto.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.border
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
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString


@Serializable
data class Contactov2(
    val id: Int,
    var name: String,
    var phone: String,
    var email: String
)
private const val CONTACTS_FILE_NAME = "contacts.txt"
fun saveContactsToFile(context: Context, contacts: List<Contactov2>) {
    val json = Json.encodeToString(contacts)

    val file = File(context.filesDir, CONTACTS_FILE_NAME)
    file.writeText(json)
    Log.d("FileSave", "Contacts saved to: ${file.absolutePath}")
}

fun loadContactsFromFile(context: Context): List<Contactov2> {
    val file = File(context.filesDir, CONTACTS_FILE_NAME)
    if (!file.exists()) return emptyList()

    val json = file.readText()
    return Json.decodeFromString(json)
}

@Composable
fun ContactListScreenv2() {
    val context = LocalContext.current
    var contacts by remember { mutableStateOf(loadContactsFromFile(context).toMutableStateList()) }

    // Function to add a new contact
    fun addContact(contact: Contactov2) {
        contacts.add(contact)
        saveContactsToFile(context, contacts)
    }

    // Function to delete a contact
    fun deleteContact(contact: Contactov2) {
        contacts.remove(contact)
        saveContactsToFile(context, contacts)
    }

    // Function to update a contact
    fun updateContact(updatedContact: Contactov2) {
        val index = contacts.indexOfFirst { it.id == updatedContact.id }
        if (index >= 0) {
            contacts[index] = updatedContact
            saveContactsToFile(context, contacts)
        }
    }

    // UI to display and manage contacts
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(contacts) { contact ->
                ContactItemv2(contact, onDelete = { deleteContact(contact) }, onUpdate = { updateContact(it) })
            }
        }

        // Example usage of adding a new contact
        Button(onClick = { addContact(Contactov2(3, "New Contact", "123456789", "new@contact.com")) }) {
            Text("Add Contact")
        }
    }

}

@Composable
fun ContactItemv2(contact: Contactov2, onDelete: (Contactov2) -> Unit, onUpdate: (Contactov2) -> Unit) {
    var name by remember { mutableStateOf(contact.name) }
    var phone by remember { mutableStateOf(contact.phone) }
    var email by remember { mutableStateOf(contact.email) }

    Column {
        TextField(value = name, onValueChange = { name = it })
        TextField(value = phone, onValueChange = { phone = it })
        TextField(value = email, onValueChange = { email = it })

        Row {
            Button(onClick = { onDelete(contact) }) {
                Text("Delete")
            }
            Button(onClick = {
                val updatedContact = contact.copy(name = name, phone = phone, email = email)
                onUpdate(updatedContact)
            }) {
                Text("Update")
            }
        }
    }
}

@Composable
fun agendaContactosv2(navController: NavController){
    ContactListScreenv2()
}
/*
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
*/
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

/*
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

*/