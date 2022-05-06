package by.kavalchuk.aliaksandr.noteapp.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import by.kavalchuk.aliaksandr.noteapp.model.Note

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE FROM notes_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM notes_table where id = :id")
    fun getById(id: Int): Note?

    @Insert
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}





















