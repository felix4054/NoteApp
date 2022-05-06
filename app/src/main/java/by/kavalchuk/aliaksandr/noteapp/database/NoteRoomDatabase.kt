package by.kavalchuk.aliaksandr.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.kavalchuk.aliaksandr.noteapp.database.room.dao.NoteRoomDao
import by.kavalchuk.aliaksandr.noteapp.model.Note
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.NOTE_DATABASE

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteRoomDatabase: RoomDatabase() {

    abstract fun getRoomDao(): NoteRoomDao

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        fun getInstance(context: Context): NoteRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteRoomDatabase::class.java,
                        NOTE_DATABASE
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}