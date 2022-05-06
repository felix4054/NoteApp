package by.kavalchuk.aliaksandr.noteapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kavalchuk.aliaksandr.noteapp.model.Note
import by.kavalchuk.aliaksandr.noteapp.utils.TYPE_FIREBASE
import by.kavalchuk.aliaksandr.noteapp.utils.TYPE_ROOM
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@HiltViewModel
//class MainViewModel @Inject constructor(private val application: Application): ViewModel(){
class MainViewModel @Inject constructor() : ViewModel() {

    val readAllData: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    private val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readAllData.value =
            when (dbType.value) {
                TYPE_ROOM -> {
                    listOf(
                        Note(title = "Title 1", subtitle = "Subtitle 1 for text"),
                        Note(title = "Title 2", subtitle = "Subtitle 2 for text"),
                        Note(title = "Title 3", subtitle = "Subtitle 3 for text"),
                        Note(title = "Title 4", subtitle = "Subtitle 4 for text")
                    )
                }
                TYPE_FIREBASE -> listOf()
                else -> listOf()
            }
    }


    fun initDataBase(type: String) {
        dbType.value = type
        Log.d("checkData", "MainViewModel initDatabase with type: $type")
    }
}












