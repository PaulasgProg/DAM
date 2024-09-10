package com.example.android_xml_eventoskotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.botones.Ej01VariantesCallbackActivity
import com.example.android_xml_eventoskotlin.botones.Ej02ContadoresActivity
import com.example.android_xml_eventoskotlin.botones.Ej03BotonCambiaBotonActivity
import com.example.android_xml_eventoskotlin.botones.Ej04Contadores2Activity
import com.example.android_xml_eventoskotlin.checkboxes.Ej05CheckBoxes1Activity
import com.example.android_xml_eventoskotlin.checkboxes.Ej06CheckBoxes2Activity
import com.example.android_xml_eventoskotlin.checkboxes.Ej07CheckBoxes3Activity
import com.example.android_xml_eventoskotlin.databinding.ActivityMainBinding
import com.example.android_xml_eventoskotlin.ejercicios.Ejercicio01Activity
import com.example.android_xml_eventoskotlin.ejercicios.Ejercicio02Activity
import com.example.android_xml_eventoskotlin.imagen.ImageMain
import com.example.android_xml_eventoskotlin.intents.Ejercicio03Activity
import com.example.android_xml_eventoskotlin.radiobutton.Ej08RadioGroupActivity
import com.example.android_xml_eventoskotlin.toggle_switch.Ej09DobleToggleActivity

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button01.setOnClickListener { startActivity(Intent(this, Ej01VariantesCallbackActivity::class.java)) }
        binding.button02.setOnClickListener { startActivity(Intent(this, Ej02ContadoresActivity::class.java)) }
        binding.button03.setOnClickListener { startActivity(Intent(this, Ej03BotonCambiaBotonActivity::class.java)) }
        binding.button04.setOnClickListener { startActivity(Intent(this, Ej04Contadores2Activity::class.java)) }
        binding.button05.setOnClickListener { startActivity(Intent(this, Ej05CheckBoxes1Activity::class.java)) }
        binding.button06.setOnClickListener { startActivity(Intent(this, Ej06CheckBoxes2Activity::class.java)) }
        binding.button07.setOnClickListener { startActivity(Intent(this, Ej07CheckBoxes3Activity::class.java)) }
        binding.button08.setOnClickListener { startActivity(Intent(this, Ej08RadioGroupActivity::class.java)) }
        binding.button09.setOnClickListener { startActivity(Intent(this, Ej09DobleToggleActivity::class.java)) }
        binding.buttonEjercicio01.setOnClickListener { startActivity(Intent(this, Ejercicio01Activity::class.java)) }
        binding.buttonEjercicio02.setOnClickListener { startActivity(Intent(this, Ejercicio02Activity::class.java)) }
        binding.buttonEjercicio03.setOnClickListener { startActivity(Intent(this, Ejercicio03Activity::class.java)) }

        binding.buttonEjercicioImagen.setOnClickListener { startActivity(Intent(this, ImageMain::class.java)) }

    }
}