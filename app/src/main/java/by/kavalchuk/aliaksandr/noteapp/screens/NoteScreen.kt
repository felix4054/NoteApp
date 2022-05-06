package by.kavalchuk.aliaksandr.noteapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.kavalchuk.aliaksandr.noteapp.MainViewModel
import by.kavalchuk.aliaksandr.noteapp.model.Note
import by.kavalchuk.aliaksandr.noteapp.ui.theme.NoteAppTheme
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.NONE

@Composable
fun NoteScreen(navController: NavHostController, mainViewModel: MainViewModel, noteId: String?) {

    val notes = mainViewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull{it.id == noteId?.toInt()} ?: Note(title = NONE, subtitle = NONE)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
//                elevation = 6.dp
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = note.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                    Text(
                        text = note.subtitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteScreen() {
    NoteAppTheme {
        NoteScreen(
            navController = rememberNavController(),
            mainViewModel = hiltViewModel(),
            noteId = "1"
        )
    }
}













