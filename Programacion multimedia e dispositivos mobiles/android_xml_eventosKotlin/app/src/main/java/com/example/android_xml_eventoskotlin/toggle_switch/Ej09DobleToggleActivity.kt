package com.example.android_xml_eventoskotlin.toggle_switch

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.databinding.ActivityEj09DobleToggleBinding

class Ej09DobleToggleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEj09DobleToggleBinding

    private var isChecked = false
        set(value) {
            field = value
            binding.switch1.isChecked = value
            binding.toggleButton.isChecked = value
            binding.tv.visibility =
                if (value) View.VISIBLE
                else View.INVISIBLE
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEj09DobleToggleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toggleButton.setOnClickListener { isChecked = !isChecked }
        binding.switch1.setOnClickListener { isChecked = !isChecked }

    }

}