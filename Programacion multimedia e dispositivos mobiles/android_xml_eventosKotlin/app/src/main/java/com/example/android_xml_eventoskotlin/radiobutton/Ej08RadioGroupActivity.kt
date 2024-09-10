package com.example.android_xml_eventoskotlin.radiobutton

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.R
import com.example.android_xml_eventoskotlin.databinding.ActivityEj08RadioGroupBinding

//https://developer.android.com/guide/topics/ui/controls/radiobutton


class Ej08RadioGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEj08RadioGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEj08RadioGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Nuevo Evento del CheckBox
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.cash -> Toast.makeText(this, "Contado", Toast.LENGTH_SHORT).show()
                R.id.card -> Toast.makeText(this, "Tarjeta", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Transferencia", Toast.LENGTH_SHORT).show()
            }
        }

        //Evento del Botón
        binding.chooseBtn.setOnClickListener {
            when (binding.radioGroup.checkedRadioButtonId) {
                binding.cash.id -> binding.tv07.text = "Forma Pago 'Contado'."
                binding.card.id -> binding.tv07.text = "Forma Pago 'Tarjeta'."
                else -> binding.tv07.text = "Forma Pago 'Transferencia'."
            }
        }

    }

}