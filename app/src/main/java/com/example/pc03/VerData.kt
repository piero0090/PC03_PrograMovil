package com.example.pc03

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pc03.fragments.PersonasFragment

class VerData : AppCompatActivity() {

    private val fragmentPersonas = PersonasFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.datatool)

        //val toast = Toast.makeText(this, "Añade una fecha", Toast.LENGTH_SHORT)
        //toast.show()

         val ft = supportFragmentManager.beginTransaction()
         ft.add(R.id.fcvSecciones, fragmentPersonas)
         ft.commit()
    }
}