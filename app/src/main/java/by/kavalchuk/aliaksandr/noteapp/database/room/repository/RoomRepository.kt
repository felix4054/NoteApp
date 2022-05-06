package by.kavalchuk.aliaksandr.noteapp.database.room.repository

import androidx.lifecycle.LiveData
import by.kavalchuk.aliaksandr.noteapp.database.DatabaseRepository
import by.kavalchuk.aliaksandr.noteapp.database.room.dao.NoteRoomDao
import by.kavalchuk.aliaksandr.noteapp.model.Note
import javax.inject.Inject

class RoomRepository @Inject constructor(private val noteRoomDao: NoteRoomDao): DatabaseRepository {

    override val readAllData: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
    }
}