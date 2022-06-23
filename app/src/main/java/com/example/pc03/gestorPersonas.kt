package com.example.pc03

import android.content.Context
import com.example.pc03.models.Personas
import com.example.pc03.room.AppDatabase
import com.example.pc03.room.Dao.PersonaRoomDAO
import com.example.pc03.room.Models.PersonaRoom

class gestorPersonas {

    fun obtenerListaPersonasRoom(context: Context, fecha : String): List<Personas>{
        /*val daoPersonas: PersonaRoomDAO = AppDatabase.getInstance(
            context).getPersonas(fecha)
        println("hola")*/

        val daoPersonas: PersonaRoomDAO = AppDatabase.getInstance(context).getPersonasDao()

        val listaPersonasRoom= daoPersonas.getDepartmentos(fecha)
        println(listaPersonasRoom.size)
        val listaPersonas = listaPersonasRoom.map {
            Personas(it.fecha_corte, it.departamento, it.provincia, it.distrito, it.metodo,it.edad,
                it.sexo, it.fecha_resultado, it.ubi_geo, it.userid, it.cantidad)
        }
        return listaPersonas
    }
/*
    fun guardarListPersonasRoom (context: Context, personas: List<Personas>){
        val daoPersonas: PersonaRoomDAO= AppDatabase.getInstance(
            context).getPersonasDao()

        personas.forEach{
            daoPersonas.insertPersonas(
                PersonaRoom(it.fecha_corte, it.departamento, it.provincia, it.distrito, it.metodo,
                    it.edad, it.sexo, it.fecha_resultado, it.ubi_geo, it.userid)
            )
        }
    }*/
}