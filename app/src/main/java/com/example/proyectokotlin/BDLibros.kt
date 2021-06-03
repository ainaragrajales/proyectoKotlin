package com.example.proyectokotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Libro::class], version = 1, exportSchema = false)
abstract class BDLibros:RoomDatabase() {

    abstract fun miDAO(): LibrosDAO

    companion object{
        @Volatile
        private var INSTANCE: BDLibros? = null

        fun getDatabase (context: Context):BDLibros {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BDLibros::class.java,
                    "misLibros_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}