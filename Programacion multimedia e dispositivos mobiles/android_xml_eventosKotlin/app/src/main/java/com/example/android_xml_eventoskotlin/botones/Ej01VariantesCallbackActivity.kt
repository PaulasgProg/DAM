package com.example.android_xml_eventoskotlin.botones

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.R
import com.example.android_xml_eventoskotlin.databinding.ActivityEj01VariantesCallbackBinding


const val LOG_TAG = ":::"

/** Función que representa un determinado comportamiento. A modo de ejemplo, escribir un log */
fun accionAsociadaAEvento(mensaje: String) = Log.i(LOG_TAG, mensaje)


class Ej01VariantesCallbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEj01VariantesCallbackBinding


    /** Clase interna que hereda de la interfaz OnclickListener */
    class MyOnClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            accionAsociadaAEvento("Hola")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEj01VariantesCallbackBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /** Igual que en Java existen las clases anónimas
         * (se utiliza este concepto ya que Java no contempla objetos que no sean instancias de una clase),
         * en Kotlin podremos hablar directamente de objetos anónimos */
        val onClickListener1 = object : View.OnClickListener {
            override fun onClick(v: View) {
                accionAsociadaAEvento("Hola")
            }
        }

        /** Asignamos este listener a un botón */
        binding.button10.setOnClickListener(onClickListener1)


        /** Equivalente a listener mediante clase anónima en Java */
        binding.button11.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Toast.makeText(
                    this@Ej01VariantesCallbackActivity,
                    getString(R.string.hola_desde, (v as Button).text),
                    Toast.LENGTH_SHORT
                ).show()
                binding.textView.text = getString(R.string.has_pulsado, (v as Button).text)
            }
        }
        )

        /** Igual que sucede en Java con las interfaces funcionales
         * (ver https://github.com/avidaldo/android-eventos-java/blob/pasando-funcion-lambda/EventosJava/app/src/main/java/com/example/eventosjava/MainActivity.java),
         * las interfaces que tienen un único método abstracto (single abstract method interface)
         * pueden ser sustituidas por una función lambda  */
        binding.button12.setOnClickListener { v: View? ->
            Toast.makeText(this, getString(R.string.hola_desde, (v as Button).text), Toast.LENGTH_SHORT).show()
            binding.textView.text = getString(R.string.has_pulsado, (v as Button).text)
            /* También se puede castear a TextView ya que es la clase padre de Button donde
            se introduce el atributo text (Button es un tipo de TextView) */
        }


        /** Y en Kotlin, además de una función lambda (que sería lo normal) podría pasarse directamente
        una función anónima, ya que Kotlin permite tratar a las funciones como objetos */
        binding.button13.setOnClickListener(
            fun(v: View?) {
                Toast.makeText(
                    this,
                    getString(R.string.hola_desde, (v as Button).text),
                    Toast.LENGTH_SHORT
                ).show()
                binding.textView.text = getString(R.string.has_pulsado, v.text)
                /* Como ya ha sido casteado previamente, no es necesario volver a hacerlo */
            }
        )


        /** El tipo del parámetro puede ser inferido por el compilador */
        binding.button14.setOnClickListener { v ->
            Toast.makeText(
                this,
                getString(R.string.hola_desde, (v as Button).text),
                Toast.LENGTH_SHORT
            ).show()
            binding.textView.text = getString(R.string.has_pulsado, (v as Button).text)
        }

        /** Cuando hay un solo parámetro de entrada, puede omitirse y utilizar la palabra reservada "it"  */
        binding.button15.setOnClickListener {
            Toast.makeText(
                this,
                getString(R.string.hola_desde, (it as Button).text),
                Toast.LENGTH_SHORT
            ).show()
            binding.textView.text = getString(R.string.has_pulsado, it.text)
        }

    }

}