@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ejerciciopresupuesto.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ejerciciopresupuesto.R

data class ProductoSuper(
    val nombre: String,
    val precio: Double,
    val imagenResId: Int // ID de recurso de imagen para la imagen del producto
)
@Composable
fun alertDialogContent(onDismiss:()->Unit,items: List<ProductoSuper>){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Carrito de la compra") },
        text = { 
               LazyColumn { items(items){   item ->
                   Text(text = item.nombre)
                   
               } } 
               },
        confirmButton = {
            Button(onClick = onDismiss ) {
                Text(text = "Aceptar")
            }
        },dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Cancelar")
            }
        })
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListaCompra(
    items: List<ProductoSuper>,
    onItemCheckedChange: (ProductoSuper, Boolean) -> Unit,
    onItemRemoved: (ProductoSuper) -> Unit,
    onAllItemsCheckedChange: (Boolean) -> Unit,
    itemCount: Int,
    itemsSeleccionados:List<ProductoSuper>
) {
    var allItemsChecked by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Compra",style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                ) },
                backgroundColor =  MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                elevation = AppBarDefaults.TopAppBarElevation,
                actions = {
                    Checkbox(
                        checked = allItemsChecked,
                        onCheckedChange = { isChecked ->
                            allItemsChecked = isChecked
                            onAllItemsCheckedChange(isChecked)
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary,
                            uncheckedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
                        )
                    )
                    IconButton(onClick = {
                       showDialog=true
                    }) {
                        Box{
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Carrito de compras",
                                tint = Color.White
                            )
                            if (itemCount > 0) {
                                Surface(
                                    color = Color.Magenta,
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .size(18.dp)
                                        .align(Alignment.TopEnd)
                                ) {
                                    Text(
                                        text = itemCount.toString(),
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    ){
        if (showDialog){
            alertDialogContent (onDismiss = {showDialog=false}, items = itemsSeleccionados)
        }
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(items) { item ->
                ListaCompraItem(
                    producto = item,
                    onCheckedChange = { isChecked ->
                        onItemCheckedChange(item, isChecked)
                        if (!isChecked) {
                            allItemsChecked = false // Reset allItemsChecked if an item is unchecked
                        }
                    },
                    onRemoved = {
                        onItemRemoved(item)
                        allItemsChecked = false // Reset allItemsChecked if an item is removed
                    }
                )
            }
        }
    }



}

@Composable
fun ListaCompraItem(
    producto:ProductoSuper,
    onCheckedChange: (Boolean) -> Unit,
    onRemoved: () -> Unit
) {
    var seleccionado by remember {
        mutableStateOf(false)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = seleccionado,
            onCheckedChange = {
                seleccionado = it
                onCheckedChange(it)}
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(producto.imagenResId),
            contentDescription = "Imagen del producto",
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "$${producto.precio}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onRemoved) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar",
                tint = Color.Red
            )
        }
    }
}
@Composable
fun lista(navController: NavController) {
    var itemCount by remember { mutableStateOf(0) }
    var listaproductos = listOf(
        ProductoSuper("Manzanas", 2.5, R.drawable.manzana),
        ProductoSuper("Plátanos", 1.8, R.drawable.platano),
        ProductoSuper("Leche", 1.0, R.drawable.leche),
        ProductoSuper("Pan", 1.2, R.drawable.pan)
    )

    // Recordar el estado de los elementos seleccionados
    var itemsSeleccionados = remember { mutableStateListOf<ProductoSuper>() }

    ListaCompra(
        items = listaproductos,
        onItemCheckedChange = { item, isChecked ->
            // Actualizar el estado del elemento seleccionado
            if (isChecked) {
                itemsSeleccionados.add(item)
            } else {
                itemsSeleccionados.remove(item)
            }
        },
        onItemRemoved = { item ->
            //items.remove(item)
            itemsSeleccionados.remove(item)
        },
        onAllItemsCheckedChange = { isChecked ->
            if (isChecked) {
                if (itemsSeleccionados.size.equals(4)){

                }else{
                    itemsSeleccionados.clear()
                    itemsSeleccionados.addAll(listaproductos)
                }

            } else {
                itemsSeleccionados.clear()
            }
        },
        itemCount= itemCount,
        itemsSeleccionados = itemsSeleccionados
    )
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Mostrar los elementos seleccionados en un Text
        Text(text = "Elementos seleccionados: ", modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(top = 200.dp))
        LazyColumn (modifier = Modifier.align(Alignment.BottomStart)){
            items(itemsSeleccionados) { item ->
                Text(text = "${item.nombre}: ${item.precio}")
            }
        }

        Button(onClick = { itemCount=itemsSeleccionados.size }, modifier = Modifier.align(Alignment.BottomEnd)) {
            Text(text = "Añadir al carrito")
        }
    }
    

}
