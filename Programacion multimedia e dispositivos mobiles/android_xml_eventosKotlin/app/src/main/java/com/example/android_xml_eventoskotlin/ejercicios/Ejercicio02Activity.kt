package com.example.android_xml_eventoskotlin.ejercicios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_xml_eventoskotlin.R
import com.example.android_xml_eventoskotlin.databinding.ActivityEjercicio02Binding
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.text.DecimalFormat

class Ejercicio02Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio02Binding

    companion object {
        /* Las conversiones entre divisan cambian cada día, así que no se podría definir una fija.
        Una aplicación real debería conectarse a Internet y solicitar la conversión a alguna API. */
        const val EUR_POR_DOLLAR = 0.83
    }

    private val decimalFormat = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio02Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button02cambiar.setOnClickListener {

            try {
                val valor: Double = binding.etValor03.text.toString().toDouble()
                if (BigDecimal.valueOf(valor).scale() > 2) {
                    binding.tvConversion.text = "Una cifra en esa divisa no debe tener más de dos decimales."

                }
                else {
                    val valorConvertido: Double
                    when (binding.rgConversion.checkedRadioButtonId) {
                        R.id.rbDolEur -> { // $ a €
                            valorConvertido = valor * EUR_POR_DOLLAR
                            binding.tvConversion.text = "${decimalFormat.format(valor)} $ equivalen a ${decimalFormat.format(valorConvertido)} €"
                        }
                        R.id.rbEurDol -> { // € a $
                            valorConvertido = valor / EUR_POR_DOLLAR
                            binding.tvConversion.text = "${decimalFormat.format(valor)} € equivalen a ${decimalFormat.format(valorConvertido)} $"
                        }
                        else -> {
                            binding.tvConversion.text = "Debe seleccionar una conversión"
                        }
                    }

                }
            } catch (e: NumberFormatException) {
                binding.tvConversion.text = "Debe introducir un valor numérico"
            }


        }

    }

}