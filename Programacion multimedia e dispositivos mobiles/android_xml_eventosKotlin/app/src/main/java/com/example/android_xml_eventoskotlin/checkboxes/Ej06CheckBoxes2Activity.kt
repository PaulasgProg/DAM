package com.example.android_xml_eventoskotlin.checkboxes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.databinding.ActivityCheckBoxesBinding

class Ej06CheckBoxes2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckBoxesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckBoxesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Actualizamos el estado de los checkboxes
        binding.cbFresa.isChecked = Helado.fresa
        binding.cbPistacho.isChecked = Helado.pistacho
        binding.cbVainilla.isChecked = Helado.vainilla

        binding.cbFresa.setOnCheckedChangeListener { _, isChecked ->
            Helado.fresa = isChecked
            binding.tvHelado.text = Helado.toString()
        }
        binding.cbVainilla.setOnCheckedChangeListener { _, isChecked ->
            Helado.vainilla = isChecked
            binding.tvHelado.text = Helado.toString()
        }
        binding.cbPistacho.setOnCheckedChangeListener { _, isChecked ->
            Helado.pistacho = isChecked
            binding.tvHelado.text = Helado.toString()
        }
    }


}