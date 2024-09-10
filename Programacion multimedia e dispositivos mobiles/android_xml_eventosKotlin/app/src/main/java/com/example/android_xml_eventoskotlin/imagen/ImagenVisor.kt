package com.example.android_xml_eventoskotlin.imagen

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.R

class ImagenVisor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_con_imagen_visor)

        //Obteniendo la instancia del TextView
        var image_name = findViewById<View>(R.id.tvName) as TextView

        //Obteniendo la instancia del Intent
        val intent = intent

        //Extrayendo el extra de tipo cadena
        val name = intent.getStringExtra(EXTRA_NOMBRE)

        //Seteando el valor del extra en el TextView
        image_name.setText(name)
    }

}
