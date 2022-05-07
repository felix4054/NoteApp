package by.kavalchuk.aliaksandr.noteapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import by.kavalchuk.aliaksandr.noteapp.navigation.NavRoute
import by.kavalchuk.aliaksandr.noteapp.ui.theme.NoteAppTheme
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.ADD_NEW_NOTE
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.NOTE_SUBTITLE
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.NOTE_TITLE
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.SAVE_NOTE
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Screens.MAIN_SCREEN
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Screens.START_SCREEN

@Composable
fun AddScreen(navController: NavHostController,  mainViewModel: MainViewModel) {

    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var isButtonEnable by remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = ADD_NEW_NOTE,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 32.dp)
            )
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    isButtonEnable = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                label = { Text(text = NOTE_TITLE) },
                isError = title.isEmpty()
            )
            OutlinedTextField(
                value = subtitle,
                onValueChange = {
                    subtitle = it
                    isButtonEnable = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                label = { Text(text = NOTE_SUBTITLE) },
                isError = subtitle.isEmpty()
            )
            Button(
                enabled = isButtonEnable,
                onClick = {
                    mainViewModel.addNote(note = Note(title = title, subtitle = subtitle)) {
                        navController.popBackStack(MAIN_SCREEN, inclusive = false)
//                        navController.navigate(NavRoute.Main.route) {
//                            popUpTo(MAIN_SCREEN) { saveState = true }
//                            launchSingleTop = true
//                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = SAVE_NOTE)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNAddScreen() {
    NoteAppTheme {
        AddScreen(
            navController = rememberNavController(),
            mainViewModel = hiltViewModel()
        )
    }
}










