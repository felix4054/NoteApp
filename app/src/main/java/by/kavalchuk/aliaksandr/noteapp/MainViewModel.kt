package by.kavalchuk.aliaksandr.noteapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.kavalchuk.aliaksandr.noteapp.database.NoteRoomDatabase
import by.kavalchuk.aliaksandr.noteapp.database.firebase.repository.AppFirebaseRepository
import by.kavalchuk.aliaksandr.noteapp.database.room.repository.AppRoomRepository
import by.kavalchuk.aliaksandr.noteapp.model.Note
import by.kavalchuk.aliaksandr.noteapp.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    application: Application
) : ViewModel() {

    private val context = application
    val searchResults = MutableLiveData<List<Note>>()

    fun initDataBase(type: String, onSuccess: () -> Unit) {
        Log.d("checkData", "MainViewModel initDatabase with type: $type")

        when (type) {
            TYPE_ROOM -> {
                val dao = NoteRoomDatabase.getInstance(context).getRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDataBase(
                    { onSuccess() },
                    { Log.d("checkData", "Error Firebase: $it") }
                )
            }
        }
    }

    fun readAllNotes() = REPOSITORY.readAllData

    // Асинхронный поиск
    fun findNote(title: String) {
        viewModelScope.launch(Dispatchers.Main) {
            searchResults.value = REPOSITORY.asyncFind(title)
        }
    }

    // Добавить
    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    // Обновить
    fun updateNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    // Удалить
    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun signOut(onSuccess: () -> Unit) {
        when (DB_TYPE.value) {
            TYPE_FIREBASE,
            TYPE_ROOM -> {
                REPOSITORY.signOut()
                DB_TYPE.value = Constants.Keys.EMPTY
                onSuccess()
            }
            else -> Log.d("checkData", "signOut: ELSE: ${DB_TYPE.value}")
        }
    }

}












