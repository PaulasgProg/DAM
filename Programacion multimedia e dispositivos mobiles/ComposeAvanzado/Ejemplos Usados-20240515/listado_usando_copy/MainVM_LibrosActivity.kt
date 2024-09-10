package com.example.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.viewmodel.otromain.LibroViewModel
import com.example.viewmodel.otromain.UsuarioViewModel
import com.example.viewmodel.ui.screens.CambioBackgroundScreen
import com.example.viewmodel.ui.screens.MainScreenFuncVersionFinal
import com.example.viewmodel.ui.screens.MainScreenFuncVersionInicial
import com.example.viewmodel.ui.screens.MainScreenLibro

class MainVM_LibrosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //5.1.
        val LibroViewModel by viewModels<LibroViewModel>()

        setContent {

            //OPCIÓN CON LISTADO LIBRO
            //5.Uso VIEWMODEL
            MainScreenLibro(LibroViewModel)
        }
    }
}
