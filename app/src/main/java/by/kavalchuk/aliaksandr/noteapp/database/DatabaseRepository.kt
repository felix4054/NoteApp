package by.kavalchuk.aliaksandr.noteapp.database

import androidx.lifecycle.LiveData
import by.kavalchuk.aliaksandr.noteapp.model.Note


interface DatabaseRepository {

    val readAllData: LiveData<List<Note>>

    suspend fun create(note: Note, onSuccess: () -> Unit)

    suspend fun update(note: Note, onSuccess: () -> Unit)

    suspend fun delete(note: Note, onSuccess: () -> Unit)

    suspend fun asyncFind(title: String): List<Note>?
}