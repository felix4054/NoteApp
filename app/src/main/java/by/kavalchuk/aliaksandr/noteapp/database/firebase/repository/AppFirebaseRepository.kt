package by.kavalchuk.aliaksandr.noteapp.database.firebase.repository

import android.util.Log
import androidx.lifecycle.LiveData
import by.kavalchuk.aliaksandr.noteapp.database.DatabaseRepository
import by.kavalchuk.aliaksandr.noteapp.database.firebase.AllNotesLiveData
import by.kavalchuk.aliaksandr.noteapp.model.Note
import by.kavalchuk.aliaksandr.noteapp.utils.Constants
import by.kavalchuk.aliaksandr.noteapp.utils.FIREBASE_ID
import by.kavalchuk.aliaksandr.noteapp.utils.LOGIN
import by.kavalchuk.aliaksandr.noteapp.utils.PASSWORD
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AppFirebaseRepository : DatabaseRepository {

    private var mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

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

    override val readAllData: LiveData<List<Note>> = AllNotesLiveData()

    override suspend fun asyncFind(title: String): List<Note>? {
        TODO("Not yet implemented")
    }

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteId = database.push().key.toString()
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.SUBTITLE] = note.subtitle

        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener {
                Log.d("checkData", "Add Firebase success")
                onSuccess()
            }
            .addOnFailureListener {
                Log.d("checkData", "Error Firebase(failed to add new notes)")
            }

    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        val noteId = note.firebaseId
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.SUBTITLE] = note.subtitle

        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener {
                Log.d("checkData", "Update Firebase success")
                onSuccess()
            }
            .addOnFailureListener {
                Log.d("checkData", "Error Firebase(failed to update notes)")
            }
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        database.child(note.firebaseId).removeValue()
            .addOnSuccessListener {
                Log.d("checkData", "Delete Firebase success")
                onSuccess()
            }
            .addOnFailureListener {
                Log.d("checkData", "Error Firebase(failed to delete notes)")
            }
    }
}

















