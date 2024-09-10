package com.example.android_xml_eventoskotlin.botones

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.R
import com.example.android_xml_eventoskotlin.databinding.ActivityEj04Contadores2Binding

class Ej04Contadores2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEj04Contadores2Binding


    /* Utilizamos los setters de las propiedades para actualizar automáticamente la UI */

    private var cuenta1 = 0
        set(value) {
            field = value; binding.tvCuenta1.text = field.toString()
        }
    private var cuenta2 = 0
        set(value) {
            field = value; binding.tvCuenta2.text = field.toString()
        }
    private var cuentaG = 0
        set(value) {
            field = value; binding.tvCuentaGlobal.text = field.toString()
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEj04Contadores2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            try {
                val incremento = binding.et1.text.toString().toInt()
                alerta(binding.tvIncremento1, false)
                cuenta1 += incremento
                cuentaG += incremento
            } catch (e: NumberFormatException) {  // Si el EditText de incremento no tiene un número
                alerta(binding.tvIncremento1, true)
            }
        }

        binding.button2.setOnClickListener {
            /* Haciendo lo mismo que arriba pero sin excepciones (estilo funcional),
            capturando si se recibe un null con la scope function let */

            binding.et2.text.toString().toIntOrNull()?.let {
                alerta(binding.tvIncremento2, false)
                cuenta2 += it
                cuentaG += it
            } ?: alerta(binding.tvIncremento2, true)

        }

        binding.ivReset1.setOnClickListener {
            cuentaG=cuentaG-cuenta1
            cuenta1 = 0
        }
        binding.ivReset2.setOnClickListener {
            cuentaG=cuentaG-cuenta2
            cuenta2 = 0
        }
        binding.ivResetG.setOnClickListener {
            cuentaG = 0
            cuenta1 = 0
            cuenta2 = 0
        }

    }

    private fun alerta(tv: TextView, alerta: Boolean) {
        if (alerta) {
            tv.setTextColor(Color.RED)
            Toast.makeText(this, R.string.no_incremento, Toast.LENGTH_SHORT).show()
        } else tv.setTextColor(Color.BLACK)

    }

}