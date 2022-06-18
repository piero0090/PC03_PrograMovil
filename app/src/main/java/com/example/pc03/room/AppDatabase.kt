package com.example.pc03.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pc03.room.Dao.PersonaRoomDAO
import com.example.pc03.room.Models.PersonaRoom

@Database(entities = arrayOf(PersonaRoom::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getPersonasDao(): PersonaRoomDAO

    companion object{
        private var mInstance : AppDatabase?= null
        fun getInstance(context: Context): AppDatabase{
            if(mInstance==null){
                mInstance = Room.inMemoryDatabaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java
                ).allowMainThreadQueries().build()
            }
            return mInstance!!
        }
    }
}