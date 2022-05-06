package by.kavalchuk.aliaksandr.noteapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kavalchuk.aliaksandr.noteapp.database.NoteRoomDatabase
import by.kavalchuk.aliaksandr.noteapp.database.room.repository.RoomRepository
import by.kavalchuk.aliaksandr.noteapp.utils.REPOSITORY
import by.kavalchuk.aliaksandr.noteapp.utils.TYPE_ROOM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : ViewModel() {

    private val context = application

    fun initDataBase(type: String, onSuccess: ()-> Unit) {
        Log.d("checkData", "MainViewModel initDatabase with type: $type")

        when(type) {
            TYPE_ROOM -> {
                val dao = NoteRoomDatabase.getInstance(context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }
}

class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}












