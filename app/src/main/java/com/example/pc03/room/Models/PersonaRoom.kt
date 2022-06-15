package com.example.pc03.room.Models

import androidx.room.Entity

@Entity
data class PersonaRoom(
    val id: Int,
    val sexo:String,
    val edad: Int,
    val metodo: String,
    val departamento: String,
    val provincia: String,
    val distrito: String
)
