package by.kavalchuk.aliaksandr.noteapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject


@HiltViewModel
class TodoViewModel @Inject constructor(private val application: Application): ViewModel() {
//
//    val readAllData: LiveData<List<TodoItem>>
//    private val repository: TodoRepository
//
//    init {
//       val todoDao = TodoDatabase.getInstance(application).todoDao()
//        repository  = TodoRepository(todoDao)
//    }
//
//    fun addTodo(todoItem: TodoItem) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addTodo(todoItem)
//        }
//    }
//
//    fun updateTodo(todoItem: TodoItem) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.updateTodo(todoItem)
//        }
//    }
//
//    fun deleteTodo(todoItem: TodoItem) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.updateTodo(todoItem)
//        }
//    }
//
//    fun deleteAllTodos() {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteAllTodos()
//        }
//    }
}

//class TodoViewModelFactory(
//    private val application: Application
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        @Suppress("UNCHECKED_CAST")
//        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
//            return TodoViewModel(application) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}



















