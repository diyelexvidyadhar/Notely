package com.example.notely.Data


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notely.Data.models.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Note>>
}