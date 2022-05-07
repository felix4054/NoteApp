package by.kavalchuk.aliaksandr.noteapp.database.firebase.repository

import androidx.lifecycle.LiveData
import by.kavalchuk.aliaksandr.noteapp.database.DatabaseRepository
import by.kavalchuk.aliaksandr.noteapp.model.Note
import by.kavalchuk.aliaksandr.noteapp.utils.LOGIN
import by.kavalchuk.aliaksandr.noteapp.utils.PASSWORD
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AppFirebaseRepository @Inject constructor(): DatabaseRepository {

    private var mAuth = FirebaseAuth.getInstance();

    override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToDataBase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnFailureListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }
    }

    override val readAllData: LiveData<List<Note>>
        get() = TODO("Not yet implemented")

    override suspend fun asyncFind(title: String): List<Note>? {
        TODO("Not yet implemented")
    }

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }
}