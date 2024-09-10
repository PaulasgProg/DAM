package com.example.android_xml_eventoskotlin.intents

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_xml_eventoskotlin.R
import com.example.android_xml_eventoskotlin.databinding.ActivityEjercicio03SecondBinding

class Ejercicio03SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio03SecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio03SecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuario = intent.extras?.getString(EXTRA_USUARIO)
        //val perfil = intent.extras?.getString(EXTRA_PERFIL)

        //Forma2
        val perfil =intent.getStringExtra(EXTRA_PERFIL);

        binding.tvSaludo04.setText("$perfil $usuario")



        binding.cbDespedida04.setOnCheckedChangeListener { _, isChecked ->
            binding.rgDespedida04.visibility =
                if (isChecked) View.VISIBLE  // Si se ha marcado, se muestra el RadioGroup
                else View.GONE  // Y si se se ha desmarcado, se quita
        }

        binding.btnVolver04.setOnClickListener {
            val intent = Intent(this, Ejercicio03Activity::class.java)
            val checkedDespedida = binding.rgDespedida04.checkedRadioButtonId
            if (!binding.cbDespedida04.isChecked) startActivity(intent)
            else {
                if (checkedDespedida == -1) errorNoDespedida()//si no se ha seleccionado
                else {
                    val despedida = findViewById<RadioButton>(checkedDespedida).text.toString()
                    startActivity(intent.putExtra(EXTRA_DESPEDIDA, "$despedida, $usuario."))
                }
            }
        }

    }

    private fun errorNoDespedida() {
        Toast.makeText(this, R.string.error_logout, Toast.LENGTH_SHORT).show()
    }

}