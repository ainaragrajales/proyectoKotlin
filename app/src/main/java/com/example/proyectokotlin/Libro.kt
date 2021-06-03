package com.example.proyectokotlin

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_misLibros")
data class Libro(@PrimaryKey (autoGenerate = true) var id:Int = 0,
                 @NonNull @ColumnInfo (name = "titulo") var titulo:String,
                 @NonNull @ColumnInfo (name = "autor") var autor:String,
                 @NonNull @ColumnInfo (name = "genero") var genero:String,
                 @NonNull @ColumnInfo (name = "fecha") var fecha:String,
                 @NonNull @ColumnInfo (name = "imagen") var imagen:String,
                 @NonNull @ColumnInfo (name = "leido") var leido:String)
