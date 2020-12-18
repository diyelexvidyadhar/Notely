package com.example.notely.Data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notely.Data.NoteDatabase
import com.example.notely.Data.models.Note
import com.example.notely.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//android view model contains application context
class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteDao = NoteDatabase.invoke(application).getNoteDao()
    private val repository: NoteRepository
  val getAllData: LiveData<List<Note>>

    init {
        repository = NoteRepository(noteDao)
        getAllData = repository.getAllNotes
    }
    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertNote(note)
    }
    }
    fun updateData(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNote(note)
        }
    }
    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(note)
        }
    }
    fun searchDatabase(searchQuery:String) : LiveData<List<Note>>{
        return repository.searchDatabase(searchQuery)
    }
}