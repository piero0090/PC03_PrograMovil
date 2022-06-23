package com.example.pc03

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pc03.fragments.PersonasFragment
import com.example.pc03.models.Personas

class VerData : AppCompatActivity() {

    private val fragmentPersonas = PersonasFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.datatool)
        val ft = supportFragmentManager.beginTransaction()
        //val toast = Toast.makeText(this, "Añade una fecha", Toast.LENGTH_SHORT)
        //toast.show()


        val btnBuscar = findViewById<Button>(R.id.btnBuscar)
        btnBuscar.setOnClickListener {
            ft.add(R.id.fcvSecciones, fragmentPersonas)
            ft.commit()
        }
    }
}