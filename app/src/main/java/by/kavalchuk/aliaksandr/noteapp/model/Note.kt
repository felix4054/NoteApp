package by.kavalchuk.aliaksandr.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.NOTES_TABLE

@Entity(tableName = NOTES_TABLE)
data class Note(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val title: String = "",

    @ColumnInfo
    val subtitle: String = "",
    val firebaseId: String = ""


)
