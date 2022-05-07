package by.kavalchuk.aliaksandr.noteapp.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.kavalchuk.aliaksandr.noteapp.MainViewModel
import by.kavalchuk.aliaksandr.noteapp.model.Note
import by.kavalchuk.aliaksandr.noteapp.navigation.NavRoute
import by.kavalchuk.aliaksandr.noteapp.ui.theme.NoteAppTheme
import by.kavalchuk.aliaksandr.noteapp.utils.Constants
import by.kavalchuk.aliaksandr.noteapp.utils.DB_TYPE
import by.kavalchuk.aliaksandr.noteapp.utils.TYPE_FIREBASE
import by.kavalchuk.aliaksandr.noteapp.utils.TYPE_ROOM

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {

    var notes = mainViewModel.readAllNotes().observeAsState(listOf()).value

    var title by remember { mutableStateOf("") }
    val searchResults = mainViewModel.searchResults.observeAsState(listOf()).value
    var searching by remember { mutableStateOf(false) }
    var isButtonEnable by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.Add.route)
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Icons",
                    tint = Color.White
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    isButtonEnable = title.isNotEmpty()
                },
                label = { Text(text = Constants.Keys.NOTE_TITLE) },
                isError = title.isEmpty()
            )
            Button(
                enabled = isButtonEnable,
                onClick = {
                searching = true
                mainViewModel.findNote(title)
            }) {
                Text("Search")
            }
            LazyColumn (
//                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val listNote = if (searching) searchResults else notes
                items(listNote) { note ->
                    NoteItem(note = note, navController = navController)
                }
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController) {

    val noteId = when(DB_TYPE.value) {
        TYPE_FIREBASE ->note.firebaseId
        TYPE_ROOM -> note.id
        else -> Constants.Keys.EMPTY
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route + "/${noteId}")
            },
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = note.subtitle
            )
        }
    }
}





















