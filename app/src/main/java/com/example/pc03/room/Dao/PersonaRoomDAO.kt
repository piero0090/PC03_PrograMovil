package com.example.pc03.room.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pc03.room.Models.PersonaRoom

@Dao
interface PersonaRoomDAO {
    @Query("SELECT * FROM PersonaRoom")
    fun getPersonas() :List<PersonaRoom>

    //SELECIONAR POR FECHAS

    @Insert
    fun insertPersonas(persona: PersonaRoom)
}