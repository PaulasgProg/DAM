package com.example.android_xml_eventoskotlin.checkboxes


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.R
import com.example.android_xml_eventoskotlin.databinding.ActivityCheckBoxesBinding

// https://developer.android.com/guide/topics/ui/controls/checkbox

class Ej05CheckBoxes1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckBoxesBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckBoxesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Actualizamos el estado de los checkboxes
        binding.cbFresa.isChecked = Helado.fresa
        binding.cbPistacho.isChecked = Helado.pistacho
        binding.cbVainilla.isChecked = Helado.vainilla

        binding.cbFresa.setOnClickListener { onCheckboxClicked(it) }
        binding.cbPistacho.setOnClickListener { onCheckboxClicked(it) }
        binding.cbVainilla.setOnClickListener { onCheckboxClicked(it) }

        //string-array
        val lista_valores: Array<String> = resources.getStringArray(R.array.checkboxes_array)
        for( i in lista_valores){
            binding.tvHelado.text =binding.tvHelado.text.toString()+lista_valores+" "
        }

        //quantity string
        val count = getNumberOfViewsAvailable()

        val viewsFound = resources.getQuantityString(R.plurals.numberOfViewsAvailable, count, count)
        binding.tvHelado.text = viewsFound.toString()

        count.toString().toIntOrNull()?.let {
            alerta(binding.tvHelado, false)
        } ?: alerta(binding.tvHelado, true)
    }

    private fun onCheckboxClicked(view: View) {

        // Actualizamos el estado con el último elemento
        val checked = (view as CheckBox).isChecked
        when (view.id) {
            R.id.cb_fresa -> Helado.fresa = checked
            R.id.cb_vainilla -> Helado.vainilla = checked
            R.id.cb_pistacho -> Helado.pistacho = checked
        }

        // Actualizamos el TextView
        binding.tvHelado.text = Helado.toString()
    }


    //A mayores para gestionar R.strings

    private fun getNumberOfViewsAvailable(): Int {
        return R.plurals.numberOfViewsAvailable.countOneBits()
    }

    private fun alerta(tv: TextView, alerta: Boolean) {
        if (alerta) {
            tv.setTextColor(Color.RED)
            Toast.makeText(this,tv.text.toString(), Toast.LENGTH_SHORT).show()
        } else tv.setTextColor(Color.BLUE)

    }
}

