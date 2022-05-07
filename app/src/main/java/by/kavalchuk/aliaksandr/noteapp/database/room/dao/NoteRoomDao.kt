package by.kavalchuk.aliaksandr.noteapp.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import by.kavalchuk.aliaksandr.noteapp.model.Note

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM NOTES_TABLE")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE FROM NOTES_TABLE")
    fun deleteAllNotes()

    @Query("SELECT * FROM NOTES_TABLE WHERE title = :title")
    fun findNote(title: String): List<Note>

    @Query("SELECT * FROM NOTES_TABLE WHERE id = :id")
    fun getById(id: Int): Note?

    @Insert
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}





















