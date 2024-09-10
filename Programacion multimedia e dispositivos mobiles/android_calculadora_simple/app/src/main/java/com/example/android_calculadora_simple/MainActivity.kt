package com.example.android_calculadora_simple


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.android_calculadora_simple.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)//YA NO Necesario

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Nuevo Evento
        binding.radiogroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {

                R.id.rb_suma -> Toast.makeText(this, "Suma", Toast.LENGTH_SHORT).show()
                R.id.rb_resta -> Toast.makeText(this, "Resta", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Multiplicación", Toast.LENGTH_SHORT).show()
            }
        }

        binding.botonoperacion.setOnClickListener {
            try {
                when (binding.radiogroup.checkedRadioButtonId) {
                    binding.rbSuma.id -> binding.tv07.text = "Resultado 'Suma'-> "+(Integer.parseInt(binding.edNum1.text.toString())+Integer.parseInt(binding.edNum2.text.toString())).toString()
                    binding.rbResta.id -> binding.tv07.text = "Resultado 'Resta-> '"+(Integer.parseInt(binding.edNum1.text.toString())-Integer.parseInt(binding.edNum2.text.toString())).toString()
                    else -> binding.tv07.text = "Resultado 'Multiplicación'-> "+(Integer.parseInt(binding.edNum1.text.toString())*Integer.parseInt(binding.edNum2.text.toString())).toString()
                }
            }catch (e:NumberFormatException){
                Toast.makeText(this,"Error, introduce 2 numeros enteros",Toast.LENGTH_SHORT).show()
            }

        }
    }

    //onClick en XML
    /*fun onmybuttonclick(view: View) {
        when (binding.radiogroup.checkedRadioButtonId) {
            binding.rbSuma.id -> binding.tv07.text = "Resultado 'Suma'-> "+(Integer.parseInt(binding.edNum1.text.toString())+Integer.parseInt(binding.edNum2.text.toString())).toString()
            binding.rbResta.id -> binding.tv07.text = "Resultado 'Resta-> '"+(Integer.parseInt(binding.edNum1.text.toString())-Integer.parseInt(binding.edNum2.text.toString())).toString()
            else -> binding.tv07.text = "Resultado 'Multiplicación'-> "+(Integer.parseInt(binding.edNum1.text.toString())*Integer.parseInt(binding.edNum2.text.toString())).toString()
        }

    }
    */
}