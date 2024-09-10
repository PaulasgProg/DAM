package com.example.viewmodel.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.viewmodel.otromain.LibroViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MainScreenLibro(LibroViewModel: LibroViewModel){

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(text = "Listado Libros") })
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(text = "Num.Libros->${LibroViewModel.state.libreria.size}")
//                Text(text = "Screen4")
            }
        }
    ) {
        //ultimo.3-
        val state = LibroViewModel.state
        //ultimo.5-
        if (state.estaCargando) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        Column(
            Modifier.padding(it)//solución2- directamente meter los padding() en el Column
                .fillMaxSize()
                .padding(35.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            //    val libreria = listOf<Libro>()
//pero no queremos hacer aqui directamente la creación, realmente se sacará de BD, API-servicio..
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                //items(LibroViewModel.libreria){//cada uno de los items->libros
                //ultimo.4-
                items(state.libreria) {
                    Column(modifier = Modifier.fillMaxWidth().clickable{
                        LibroViewModel.libroCliked(it)
                    }) {
                        Text(text = it.titulo)
                        Text(text = it.autor)
                        Divider()
                    }
                }
            }
        }
    }
}