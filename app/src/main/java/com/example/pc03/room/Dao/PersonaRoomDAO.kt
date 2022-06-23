package com.example.pc03.room.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pc03.models.Personas
import com.example.pc03.room.Models.PersonaRoom

@Dao
interface PersonaRoomDAO {
    @Query("SELECT * FROM PersonaRoom")
    fun getallPersonas() :List<PersonaRoom>

    //SELECIONAR POR FECHAS

    @Query("SELECT *, count(*) as cantidad FROM PersonaRoom where fecha_resultado=:fecha GROUP BY departamento" )
    fun getDepartmentos(fecha: String) :List<PersonaRoom>

    @Insert
    fun insertPersonas(persona: PersonaRoom)

    @Query("DELETE FROM PersonaRoom")
    fun borrarAll ()





}