package com.example.android_xml_eventoskotlin.checkboxes

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.R
import com.example.android_xml_eventoskotlin.databinding.ActivityCheckBoxesBinding


class Ej07CheckBoxes3Activity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    private lateinit var binding: ActivityCheckBoxesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckBoxesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Actualizamos el estado de los checkboxes
        binding.cbFresa.isChecked = Helado.fresa
        binding.cbPistacho.isChecked = Helado.pistacho
        binding.cbVainilla.isChecked = Helado.vainilla

        binding.cbFresa.setOnCheckedChangeListener(this)
        binding.cbVainilla.setOnCheckedChangeListener(this)
        binding.cbPistacho.setOnCheckedChangeListener(this)
    }


    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when (buttonView.id) {
            R.id.cb_fresa -> Helado.fresa = isChecked
            R.id.cb_vainilla -> Helado.vainilla = isChecked
            R.id.cb_pistacho -> Helado.pistacho = isChecked
        }
        binding.tvHelado.text = Helado.toString()
    }

}