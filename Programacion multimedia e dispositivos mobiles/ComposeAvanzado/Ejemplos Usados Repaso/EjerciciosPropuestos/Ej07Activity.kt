package com.example.composeprimeraslayouts_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Ej07Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppView07()
        }
    }

}


@Preview(showSystemUi = true)
//@Preview(device = Devices.PIXEL_C)
//@Preview
@Composable
fun AppView07() {
    Column(
        Modifier
            //.fillMaxSize()
            .background(Color.Blue)
            .absolutePadding(top = 20.dp, bottom = 80.dp)
            .padding(horizontal = 20.dp)
//            .background(Color(0xFF8BC34A)),
            .background(Color.Cyan),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "hi")
        Text(text = "teis")
        Row() {
            Text(text = "hi")
            Box(Modifier.fillMaxWidth()) {
                Text(text = "teis", Modifier.align(Alignment.CenterEnd))
            }
        }
        Row() {
            Text(text = "hi")
            Text(
                text = "teis",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )

        }
        Box(Modifier.fillMaxWidth()) {
            Text(text = "hi", Modifier.align(Alignment.CenterStart))
            Text(text = "teis", Modifier.align(Alignment.CenterEnd))
        }

    }
}
