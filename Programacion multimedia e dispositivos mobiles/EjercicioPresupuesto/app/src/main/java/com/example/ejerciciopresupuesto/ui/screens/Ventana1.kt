package com.example.ejerciciopresupuesto.ui.screens

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ejerciciopresupuesto.R

data class Producto(
    val name: String,
    val descripcion:String,
    val precio:Double,
    @DrawableRes val imagen:Int,
    var seleccionado: Boolean
)

val listaProducto= mutableListOf(
    Producto("Monitor","Monitor curvo de 24",79.99,R.drawable.monitor_,false),
    Producto("Teclado","Teclado gamer con luces",34.87,R.drawable.teclado,false),
    Producto("Altavoces","Altavoces de gran tamaño",60.79,R.drawable.altavoces,false),
    Producto("Torre","Torre gamer con gran capacidad",1200.89,R.drawable.torre,false)
)


@Composable
fun ProductItem(producto: Producto,presupuesto: Presupuesto){

        Row (modifier = Modifier
            .fillMaxWidth()
            .border(border = BorderStroke(width = 5.dp, color = Color.Black))
            .padding(bottom = 10.dp, top = 10.dp, start = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = producto.imagen),
                contentDescription = "Imagen de ${producto.name}",
                modifier = Modifier.size(200.dp))

            Text(text = producto.name)
            Text(text ="${producto.precio}" , modifier = Modifier.size(50.dp), Color.Magenta)
            Checkbox(checked = producto.seleccionado, onCheckedChange = {
                producto.seleccionado=!producto.seleccionado;
                if (producto.seleccionado) presupuesto.total+=producto.precio else presupuesto.total-=producto.precio })

        }

}

class Presupuesto(precio: Double){
    var total by mutableStateOf(precio);
}


@Composable
private fun generarLista(
    modifier: Modifier = Modifier,
    productList: List<Producto>,
    presupuesto: Presupuesto
) {
    val productos: List<Producto> = productList
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = productos) { name ->
            ProductItem(producto = name,presupuesto)
        }
    }


}

@Composable
fun ventana(presupuesto: Presupuesto){

    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Precio total: ${presupuesto.total}€")
    }
    Row (modifier = Modifier.padding(top = 25.dp)){
        generarLista(productList = listaProducto, presupuesto= presupuesto)
    }
}

@Composable
fun Ventana1funcion(navController: NavController){

    ventana(presupuesto = Presupuesto(0.0))


}
/*
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PantallaNavegacionVentana1(navController: NavController, contador: ContadorEx) {
    val navController2 = rememberNavController()
    var tamanoFuente by remember { mutableStateOf(12) }
    var expanded by remember { mutableStateOf(false) }
    var argentina by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Exam PMDP_D 2aEVA")
                },
                actions = {
                    IconButton(onClick = { contador.contar=0;
                        if (contador.contar>0) contador.iniciar="Inicializado" else contador.iniciar="Sin inicializar"}) {
                        Icon(imageVector =Icons.Default.Refresh , contentDescription =null )
                    }
                    IconButton(onClick = { contador.contar++;
                        if (contador.contar>0) contador.iniciar="Inicializado" else contador.iniciar="Sin inicializar"}) {
                        Icon(imageVector =Icons.Default.Add , contentDescription = null)
                    }
                    IconButton(onClick = { contador.contar--;
                        if (contador.contar>0) contador.iniciar="Inicializado" else contador.iniciar="Sin inicializar"}) {
                        Icon(imageVector =Icons.Default.Delete , contentDescription =null )
                    }

                }
            )
        }
    ) {

        Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Listado ${contador.iniciar}. Contador ${contador.contar}")

            Row {
                IconButton(onClick = { contador.contar=0;
                    if (contador.contar>0) contador.iniciar="Inicializado" else contador.iniciar="Sin inicializar" }) {
                    Icon(imageVector =Icons.Default.Refresh , contentDescription =null )

                }

                IconButton(onClick = { contador.contar++;
                    if (contador.contar>0) contador.iniciar="Inicializado" else contador.iniciar="Sin inicializar"}) {
                    Icon(imageVector =Icons.Default.Add , contentDescription = null)

                }

                IconButton(onClick = { contador.contar--;
                    if (contador.contar>0) contador.iniciar="Inicializado" else contador.iniciar="Sin inicializar"}) {
                    Icon(imageVector =Icons.Default.Delete , contentDescription =null )

                }


            }

            GreetingsEx(modifier = Modifier.fillMaxWidth(), contador =contador)


        }

    }
}

@Composable
fun MyApp1(modifier: Modifier = Modifier,contador: ContadorEx) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(contador) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {

        GreetingsEx(contador =shouldShowOnboarding)
    }
}



@Composable
private fun GreetingsEx(
    modifier: Modifier = Modifier,
    contador: ContadorEx
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