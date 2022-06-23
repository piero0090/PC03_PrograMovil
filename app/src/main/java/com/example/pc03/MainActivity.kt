package com.example.pc03

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pc03.room.AppDatabase
import com.example.pc03.room.Models.PersonaRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {


    private var progressbar: ProgressBar? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressbar = findViewById(R.id.ProgressBarComp)
        progressbar?.setVisibility(View.INVISIBLE)
        //var progressB: ProgressBar = findViewById(R.id.ProgressBarComp)
        //progressB.setVisibility(View.INVISIBLE)
       // progressBarComp.setVisibility(View.INVISIBLE)
       // progressB= findViewById<ProgressBar>(R.id.ProgressBarComp)

        //Boton Sincronizar
        val btnsincronizar= findViewById<Button>(R.id.btnSincronizar)
        btnsincronizar.setOnClickListener{
            progressbar?.setVisibility(View.VISIBLE)
            //progressB.setVisibility(View.VISIBLE)
            makeconnection()
            //progressB.setVisibility(View.INVISIBLE)
            btnsincronizar.setEnabled(false)
        }

        //Boton Limpiar
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnLimpiar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
               AppDatabase.getInstance(this@MainActivity).getPersonasDao().borrarAll()
            }
            btnsincronizar.setEnabled(true)
        }

        //crear otro activity
        //Boton VerData
        val btnVerData = findViewById<Button>(R.id.btnVerdatos)
        btnVerData.setOnClickListener {

            val intent = Intent(this, VerData::class.java)
            startActivity(intent)
        }
    }

    private fun makeconnection(){
        CoroutineScope(Dispatchers.IO).launch {
           // var progressBarComp = findViewById<ProgressBar>(R.id.ProgressBarComp)
            var httpurlconn : HttpURLConnection?=null
            try {
                val url = URL("https://files.minsa.gob.pe/s/eRqxR35ZCxrzNgr/download")
                httpurlconn = url.openConnection() as HttpURLConnection
                val code = httpurlconn.responseCode

                if (code!= 200){ throw IOException("ERROR $code")
                }else{println("CONECTADOOOO!!!!!!")}

                val bufferedreader = BufferedReader(InputStreamReader(httpurlconn.inputStream))

                try {
                    BufferedReader(bufferedreader).use { br->
                        var line : String?
                        for (i in 2..10000){
                           // progressBarComp.setVisibility(View.VISIBLE)
                            br.readLine().also { line=it }
                            val list : List<String> = line?.split(";")!!.toList()
                            val personInfo = PersonaRoom(list[0], list[1],list[2],list[3],list[4],
                                list[5],list[6],list[7],list[8],list[9],0)
                            /*val personInfo = PersonaRoom(list[0], list[1],list[2],list[3],list[4],
                                list[5],list[6],list[7],list[8],list[9],null)*/
                            AppDatabase.getInstance(this@MainActivity).getPersonasDao().insertPersonas(personInfo)
                        }
                    }
                    progressbar?.setVisibility(View.INVISIBLE)
                }catch (e : IOException){
                    e.printStackTrace()
                }
            }catch (ioexception : IOException){
                Log.e(this.javaClass.name, ioexception.message.toString())
            } finally {
                httpurlconn?.disconnect()
            }
        }
    }
}