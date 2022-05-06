package by.kavalchuk.aliaksandr.noteapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@HiltViewModel
//class MainViewModel @Inject constructor(private val application: Application): ViewModel(){
class MainViewModel @Inject constructor(): ViewModel(){
    fun initDataBase(type: String) {
        Log.d("checkData", "MainViewModel initDatabase with type: $type")
    }
}

//class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        @Suppress("UNCHECKED_CAST")
//        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            return MainViewModel(application = application) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel Class")
//    }
//}











