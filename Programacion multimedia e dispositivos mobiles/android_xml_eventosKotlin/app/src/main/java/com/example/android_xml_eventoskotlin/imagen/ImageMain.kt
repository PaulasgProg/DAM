package com.example.android_xml_eventoskotlin.imagen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.R
import com.example.android_xml_eventoskotlin.databinding.ActivityConImagenBinding


const val EXTRA_NOMBRE = "com.example.android_xml_eventoskotlin.imagen.NOMBRE"

class ImageMain : AppCompatActivity(){

private var show_pet_button: Button? = null
    private lateinit var binding: ActivityConImagenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_con_imagen)

        binding = ActivityConImagenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obteniendo una instancia del boton show_pet_button
        show_pet_button = findViewById(R.id.show_pet_button) as Button?

        //Registrando la escucha sobre la actividad Main
        //binding.showPetButton
        show_pet_button?.setOnClickListener{
            //Iniciando la actividad Visor
    //            val intent = Intent(this, ImagenVisor::class.java)
    //            startActivity(intent)

            //Iniciando la actividad Visor
            val intent = Intent(this, ImagenVisor::class.java)

            //Adhesión de nuestra cadena
            intent.putExtra(EXTRA_NOMBRE, "pet1.jpg")

            startActivity(intent)
        }
        binding.tvLink.setOnClickListener{
            val webpage = Uri.parse("https://www.edu.xunta.gal/centros/iesteis/aulavirtual/course/view.php?id=1428")
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(webIntent)

            // Verificar si hay aplicaciones disponibles
            val packageManager = packageManager
            val activities: List<*> = packageManager.queryIntentActivities(webIntent, 0)
            val isIntentSafe = activities.size > 0

            // Si hay, entonces ejecutamos la actividad
            if (isIntentSafe) {
                startActivity(webIntent)
            }
            else  Toast.makeText(this, "Imposible abrir el AVTeis en navegador", Toast.LENGTH_SHORT).show()
        }
    }
}