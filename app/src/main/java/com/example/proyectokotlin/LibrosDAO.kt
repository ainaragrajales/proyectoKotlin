package com.example.proyectokotlin

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LibrosDAO {
    @Query("SELECT * FROM tabla_misLibros ORDER BY id ASC")
    fun mostrarTodos(): Flow<MutableList<Libro>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(miLibro: Libro)

    @Query ("DELETE FROM tabla_misLibros")
    suspend fun borrarTodo()

    @Query("SELECT * FROM tabla_misLibros WHERE id LIKE :id")
    fun buscarPorId(id: Int): Flow<Libro>

    @Update
    suspend fun actualizar(miLibro: Libro)

    @Delete
    suspend fun borrar(miLibro: Libro)

    @Query("DELETE FROM tabla_misLibros WHERE id LIKE :id")
    suspend fun borrarPorId(id: Int)

}