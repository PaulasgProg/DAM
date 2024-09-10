package com.example.appEntregable.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun hiTeis(navController: NavController){
    app()
}

@Preview(showBackground = true)
@Composable
fun app(){
    Box(modifier = Modifier
        .background(Color.Blue)
        .fillMaxWidth()
        .height(200.dp)
        .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 60.dp)
        .background(Color.Cyan)){
        Column {
            Row (modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Text(text = "Hola", style = MaterialTheme.typography.bodySmall)
            }
            Row (modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Text(text = "buenas tardes", style = MaterialTheme.typography.bodySmall)
            }
            Row {
                Column {
                    Text(text = "HI", fontStyle = FontStyle.Italic, style = MaterialTheme.typography.bodySmall)
                    Text(text = "HI", fontStyle = FontStyle.Italic, style = MaterialTheme.typography.bodySmall)
                    Text(text = "HI", fontStyle = FontStyle.Italic, style = MaterialTheme.typography.bodySmall)
                }

                Column (modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End){

                    Text(text = "teis", fontStyle = FontStyle.Italic, style = MaterialTheme.typography.bodySmall)
                    Text(text = "teis", fontStyle = FontStyle.Italic, style = MaterialTheme.typography.bodySmall)
                    Text(text = "teis", fontStyle = FontStyle.Italic, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
