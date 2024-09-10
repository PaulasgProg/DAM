package com.example.android_xml_eventoskotlin.botones

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.R
import com.example.android_xml_eventoskotlin.databinding.ActivityEj02ContadoresBinding


class Ej02ContadoresActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEj02ContadoresBinding

    var cont1: Int = 0; var cont2: Int = 0; var contGlobal: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEj02ContadoresBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button21.setOnClickListener {
            Toast.makeText(this, getString(R.string.hola_desde, (it as Button).text), Toast.LENGTH_SHORT).show()
            cont1++
            it.text = getString(R.string.button21, cont1)
            binding.textView21.text = cont1.toString()
            binding.textView24.text = (++contGlobal).toString()
        }

        binding.button22.setOnClickListener { it: View? ->
            Toast.makeText(this, getString(R.string.hola_desde, (it as Button).text), Toast.LENGTH_SHORT).show()
            it.text = getString(R.string.button22, getString(R.string.button22empty), ++cont2)
            binding.textView22.text = cont2.toString()
            binding.textView24.text = (++contGlobal).toString()
        }


    }


}