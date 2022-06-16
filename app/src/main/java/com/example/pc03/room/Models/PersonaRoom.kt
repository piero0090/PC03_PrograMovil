package com.example.pc03.room.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonaRoom(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "fecha_corte")
    val fecha_corte : String,
    @ColumnInfo(name = "fecha_resultado")
    val fecha_resultado : String,
    @ColumnInfo(name = "ubi_geo")
    val ubi_geo : Int,
    @ColumnInfo(name = "sexo")
    val sexo:String,
    @ColumnInfo(name = "edad")
    val edad: Int,
    @ColumnInfo(name = "metodo")
    val metodo: String,
    @ColumnInfo(name = "departamento")
    val departamento: String,
    @ColumnInfo(name = "provincia")
    val provincia: String,
    @ColumnInfo(name = "distrito")
    val distrito: String
)
