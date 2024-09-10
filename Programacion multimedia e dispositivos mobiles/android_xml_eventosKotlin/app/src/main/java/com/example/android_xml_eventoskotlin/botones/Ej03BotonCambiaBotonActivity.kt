package com.example.android_xml_eventoskotlin.botones

import android.graphics.Color.RED
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.databinding.ActivityEj03BotonCambiaBotonBinding

class Ej03BotonCambiaBotonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEj03BotonCambiaBotonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEj03BotonCambiaBotonBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button32.setOnClickListener {
            Toast.makeText(this, "Todo bien", Toast.LENGTH_SHORT).show()
        }

        binding.button31.setOnClickListener {
            binding.button32.setOnClickListener {
                Toast.makeText(this, "He sido capturado", Toast.LENGTH_SHORT).show()
                binding.button32.setBackgroundColor(RED)

            }
        }

    }
}