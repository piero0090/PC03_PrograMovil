package com.example.pc03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.pc03.models.Personas
import com.example.pc03.room.AppDatabase
import com.example.pc03.room.Models.PersonaRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnsincronizar= findViewById<Button>(R.id.btnSincronizar)
        btnsincronizar.setOnClickListener{
            makeconnection()
            btnsincronizar.setEnabled(false)
        }

        /*val btnVerdata = findViewById<Button>(R.id.btnVerdatos)
        }*/

    }

    private fun makeconnection(){
        CoroutineScope(Dispatchers.IO).launch {
            var httpurlconn : HttpURLConnection?=null
            try {
                val url = URL("https://files.minsa.gob.pe/s/eRqxR35ZCxrzNgr/download")
                httpurlconn = url.openConnection() as HttpURLConnection
                val code = httpurlconn.responseCode
                if (code!= 200){
                    throw IOException("ERROR $code")
                }else{
                    println("SE PUEDEEEEEE!!!!!")
                }

                val bufferedreader = BufferedReader(
                    InputStreamReader(httpurlconn.inputStream)
                )
                try {
                    BufferedReader(bufferedreader).use { br->
                        var line : String?
                        while (br.readLine().also { line = it } != null){
                            //val gest: gestorPersonas? = null
                            //println(line)
                            val list : List<String> = line?.split(";")!!.toList()
                           // println(list)
                            //println(list[0]+"RAAAAAAAAAAAAAAAAAA")
                            val personInfo = PersonaRoom(list[0], list[1],list[2],list[3],list[4],
                                list[5],list[6],list[7],list[8],list[9],null)
                            AppDatabase.getInstance(this@MainActivity).getPersonasDao().insertPersonas(personInfo)
                         //   gest?.guardarListPersonasRoom( ,list)

                        //var result: List<String> = line!!.split(";")?.map { it.trim() }
                            //result.forEach(println(it))
                        }

                    }
                }catch (e : IOException){
                    e.printStackTrace()
                }

                TODO("FUNCION DESERIALIZAR CSV")
            }catch (ioexception : IOException){
                Log.e(this.javaClass.name, ioexception.message.toString())
            } finally {
                httpurlconn?.disconnect()
            }
        }
    }

}