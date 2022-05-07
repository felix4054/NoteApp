package by.kavalchuk.aliaksandr.noteapp.database.room.repository

import androidx.lifecycle.LiveData
import by.kavalchuk.aliaksandr.noteapp.database.DatabaseRepository
import by.kavalchuk.aliaksandr.noteapp.database.room.dao.NoteRoomDao
import by.kavalchuk.aliaksandr.noteapp.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class AppRoomRepository @Inject constructor(private val noteRoomDao: NoteRoomDao): DatabaseRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override suspend fun asyncFind(title: String): List<Note>? =
        coroutineScope.async(Dispatchers.IO) {
            return@async noteRoomDao.findNote(title = title)
        }.await()

    override val readAllData: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
        onSuccess()
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
        onSuccess()
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
        onSuccess()
    }

}