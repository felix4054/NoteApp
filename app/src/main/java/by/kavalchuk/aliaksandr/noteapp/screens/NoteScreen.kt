package by.kavalchuk.aliaksandr.noteapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import by.kavalchuk.aliaksandr.noteapp.MainViewModel
import by.kavalchuk.aliaksandr.noteapp.model.Note
import by.kavalchuk.aliaksandr.noteapp.utils.Constants
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.NONE
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.NOTE_SUBTITLE
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.NOTE_TITLE
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.UPDATE_NOTE
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Screens.MAIN_SCREEN
import by.kavalchuk.aliaksandr.noteapp.utils.DB_TYPE
import by.kavalchuk.aliaksandr.noteapp.utils.TYPE_FIREBASE
import by.kavalchuk.aliaksandr.noteapp.utils.TYPE_ROOM
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavHostController, mainViewModel: MainViewModel, noteId: String?) {

    val notes = mainViewModel.readAllNotes().observeAsState(listOf()).value
    val note = when(DB_TYPE.value) {
        TYPE_ROOM -> {
            notes.firstOrNull { it.id == noteId?.toInt() } ?: Note()
        }
        TYPE_FIREBASE -> {
            notes.firstOrNull { it.firebaseId == noteId } ?: Note()
        }
        else -> Note()
    }
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Edit note",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .padding(top = 16.dp)
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .padding(top = 6.dp),
                        value = title,
                        onValueChange = {
                            title = it
                        },
                        label = { Text(text = NOTE_TITLE) },
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .padding(top = 6.dp),
                        value = subtitle,
                        onValueChange = {
                            subtitle = it
                        },
                        label = { Text(text = NOTE_SUBTITLE) },
                        isError = subtitle.isEmpty()
                    )
                    Button(
                        onClick = {
                            mainViewModel.updateNote(
                                note = Note(
                                    id = note.id,
                                    title = title,
                                    subtitle = subtitle,
                                    firebaseId = note.firebaseId
                                )
                            ) {
                                navController.popBackStack(MAIN_SCREEN, inclusive = false)
//                                navController.navigate(NavRoute.Main.route) {
//                                    popUpTo(MAIN_SCREEN) { saveState = true }
//                                    launchSingleTop = true
//                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
//                            .padding(horizontal = 32.dp)
                    ) {
                        Text(text = UPDATE_NOTE)
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                title = note.title
                                subtitle = note.subtitle
                                bottomSheetState.show()
                            }
                        }) {
                        Text("Update")
                    }
                    Button(
                        onClick = {
                            mainViewModel.deleteNote(note = note) {
                                navController.popBackStack()
                            }
                        }) {
                        Text("Delete")
                    }
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    onClick = {
                        navController.popBackStack()
                    }) {
                    Text("Nav Back")
                }

            }
        }
    }
}














