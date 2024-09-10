package com.example.android_xml_eventoskotlin.intents

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.R
import com.example.android_xml_eventoskotlin.databinding.ActivityEjercicio03Binding

const val EXTRA_DESPEDIDA = "com.example.android_xml_eventoskotlin.intents.DESPEDIDA"
const val EXTRA_USUARIO = "com.example.android_xml_eventoskotlin.intents.USUARIO"
const val EXTRA_PERFIL = "com.example.android_xml_eventoskotlin.intents.TRATAMIENTO"

class Ejercicio03Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio03Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio03Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val mensajeRecibido = intent.getStringExtra(EXTRA_DESPEDIDA)
        if (mensajeRecibido != null)
            Toast.makeText(this, mensajeRecibido, Toast.LENGTH_SHORT).show()

        binding.button04.setOnClickListener {
            val checkedPerfil = binding.rgPerfil.checkedRadioButtonId
            val nombre: String = binding.etNombre04.text.toString()
            when {
                nombre.isEmpty() -> errorNoNombre()
                checkedPerfil == -1 -> errorNoTratamiento()
                else -> {
                    val perfil = (findViewById<RadioButton>(checkedPerfil)).text.toString()
                    toSecondActivity(perfil, nombre)
                }
            }
        }
    }

    private fun errorNoTratamiento() =
        Toast.makeText(this, R.string.error_perfil, Toast.LENGTH_SHORT).show()

    private fun errorNoNombre() =
        Toast.makeText(this, R.string.error_nombre, Toast.LENGTH_SHORT).show()

    private fun toSecondActivity(tratamiento: String, nombre: String) {
        val intent = Intent(this, Ejercicio03SecondActivity::class.java)
        intent.putExtra(EXTRA_PERFIL, tratamiento)
        intent.putExtra(EXTRA_USUARIO, nombre)
//        startActivityForResult(intent, 1)
    }

}