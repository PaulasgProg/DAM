package com.example.appEntregable.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appEntregable.examples.Calculadora
import com.example.appEntregable.examples.CalculadoraState
import com.example.appEntregable.examples.CalculadoraViewModel

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

