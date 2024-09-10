package com.example.viewmodel.repaso.conlistado.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.viewmodel.R

@Composable
fun AddBlock(
    modifier: Modifier = Modifier,
//    textButton: String = stringResource(R.string.add),
    addProduct: (String) -> Unit
) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        var text by rememberSaveable { mutableStateOf("") }

        TextField(
            value = text, onValueChange = { text = it }
        )
        Button(onClick = {
//            if (text.isNotBlank())
                addProduct(text)
            text = ""

        }) {
            Text(text = "Add" )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AddBlock_b_VM(
) {
    val focusManager = LocalFocusManager.current

    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        var text by rememberSaveable { mutableStateOf("") }

        TextField(
            value = text, onValueChange = { text = it }
        )
        Button(onClick = {
            //addProduct(text)
            text = ""
        }) {
            Text(text = "Add" //textButton
                 )
        }
    }
}