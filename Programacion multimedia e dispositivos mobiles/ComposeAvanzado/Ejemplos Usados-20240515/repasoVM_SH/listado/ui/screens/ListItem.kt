package com.example.viewmodel.repaso.conlistado.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ListItem(name: String,
                     checked: Boolean,
                     onCheckedChange: (Boolean) -> Unit,
                     onRemove: () -> Unit,
                     modifier: Modifier = Modifier) {

    Row(){
        Text(text=name)
        Checkbox(checked = checked,
            onCheckedChange = { onCheckedChange(it) }//opción1
                               //onCheckedChange//opción2
        )
        IconButton(onClick=onRemove){//accion eliminar
            Icon(Icons.Filled.Close,contentDescription="Close")
        }
    }
}