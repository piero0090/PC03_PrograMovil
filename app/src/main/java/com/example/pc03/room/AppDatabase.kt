package com.example.pc03.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pc03.room.Dao.PersonaRoomDAO
import com.example.pc03.room.Models.PersonaRoom

@Database(entities = arrayOf(PersonaRoom::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getPersonasDao(): PersonaRoomDAO
}