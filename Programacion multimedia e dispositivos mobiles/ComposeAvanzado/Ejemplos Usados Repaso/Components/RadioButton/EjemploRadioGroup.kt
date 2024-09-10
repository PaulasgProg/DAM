package com.example.compose_components.examples.RadioButton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun RadioGroupExample() {
    var selectedOption by remember { mutableStateOf("Opción 1") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Selecciona una opción:")

        RadioGroup(
            options = listOf("Opción 1", "Opción 2", "Opción 3"),
            selectedOption = selectedOption,
            onOptionSelected = { option ->
                selectedOption = option
            }
        )

        Text("Opción seleccionada: $selectedOption")
    }
}

@Composable
fun RadioGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    options.forEach { option ->
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .selectable(
                    selected = (option == selectedOption),
                    onClick = { onOptionSelected(option) }
                )
        ) {
            RadioButton(
                selected = (option == selectedOption),
                onClick = { onOptionSelected(option) }
            )
            Text(
                text = option,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}