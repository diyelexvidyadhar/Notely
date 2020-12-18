package com.example.notely.repository

import androidx.lifecycle.LiveData
import com.example.notely.Data.models.Note
import com.example.notely.Data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {
    val getAllNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
    suspend fun deleteItem(note: Note){
        noteDao.deleteNote(note)
    }
    fun searchDatabase(searchQuery: String) : LiveData<List<Note>>{
        return noteDao.searchDatabase(searchQuery)
    }
}