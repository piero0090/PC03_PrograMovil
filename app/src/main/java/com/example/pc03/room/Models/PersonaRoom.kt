package com.example.pc03.room.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonaRoom(
    @ColumnInfo(name = "fecha_corte")
    val fecha_corte : String,
    @ColumnInfo(name = "departamento")
    val departamento: String,
    @ColumnInfo(name = "provincia")
    val provincia: String,
    @ColumnInfo(name = "distrito")
    val distrito: String,
    @ColumnInfo(name = "metodo")
    val metodo: String,
    @ColumnInfo(name = "edad")
    val edad: String,
    @ColumnInfo(name = "sexo")
    val sexo:String,
    @ColumnInfo(name = "fecha_resultado")
    val fecha_resultado : String,
    @ColumnInfo(name = "ubi_geo")
    val ubi_geo : String,
    @ColumnInfo(name = "userid")
    val userid: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int?






)
