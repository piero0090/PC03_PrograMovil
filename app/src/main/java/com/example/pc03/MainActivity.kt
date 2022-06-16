package com.example.pc03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
        }

    }

    private fun makeconnection(){
        CoroutineScope(Dispatchers.IO).launch {
            var httpurlconn : HttpURLConnection?=null
            try {
                val url = URL("https://www.datosabiertos.gob.pe/dataset/casos-positivos-por-covid-19-ministerio-de-salud-minsa")
                httpurlconn = url.openConnection() as HttpURLConnection
                val code = httpurlconn.responseCode
                if (code!= 200){
                    throw IOException("ERROR $code")
                }
                val bufferedreader = BufferedReader(
                    InputStreamReader(httpurlconn.inputStream)
                )
                //CSV

            }catch (ioexception : IOException){
                Log.e(this.javaClass.name, ioexception.message.toString())
            } finally {
                httpurlconn?.disconnect()
            }
        }
    }

    /*val connection = URL ("https://www.datosabiertos.gob.pe/dataset/casos-positivos-por-covid-19-ministerio-de-salud-minsa")
        .openConnection() as HttpURLConnection

    val data = connection.inputStream.bufferedReader().use { reader->

    }*/
    /*
    private fun httpGet (myUrl: String?): String{
        val inputstream : InputStream
        val result : String

        val url : URL =URL(myUrl)
        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
        conn.connect()
        inputstream= conn.inputStream
        if(inputstream!=null){
            result= convertInputStream(inputstream)
        }else{
            result = "FUCCKKK"
        }
        return result
    }

    private fun convertInputStream(inputStream: InputStream): String {
        val bufferedReader: BufferedReader? = BufferedReader(InputStreamReader(inputStream))

        var line:String? = bufferedReader?.readLine()
        var result:String = ""

        while (line != null) {
            result += line
            line = bufferedReader?.readLine()
        }

        inputStream.close()
        return result
    }*/


}