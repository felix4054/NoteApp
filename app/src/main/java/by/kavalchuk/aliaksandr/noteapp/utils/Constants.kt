package by.kavalchuk.aliaksandr.noteapp.utils

import androidx.compose.runtime.mutableStateOf
import by.kavalchuk.aliaksandr.noteapp.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
const val FIREBASE_ID = "firebaseId"

lateinit var REPOSITORY: DatabaseRepository
lateinit var LOGIN: String
lateinit var PASSWORD: String
var DB_TYPE = mutableStateOf("")

object Constants {
    object Keys {
        const val EMPTY = ""
        const val TITLE = "title"
        const val SUBTITLE = "subtitle"
        const val NOTE_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "Add new note"
        const val NOTE_TITLE = "Note title"
        const val NOTE_SUBTITLE = "Note subtitle"
        const val SAVE_NOTE = "Save Note"
        const val UPDATE_NOTE = "Update Note"

        // Start screen -> Sign In
        const val SIGN_IN = "Sign In"
        const val LOGIN_TEXT = "Enter login"
        const val PASSWORD_TEXT = "Enter password"

        const val ID = "id"
        const val NONE = "none"
    }

    object Screens {
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
    }
}















