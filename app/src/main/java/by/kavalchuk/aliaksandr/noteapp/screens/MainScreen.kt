package by.kavalchuk.aliaksandr.noteapp.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.kavalchuk.aliaksandr.noteapp.MainViewModel
//import by.kavalchuk.aliaksandr.noteapp.MainViewModelFactory
import by.kavalchuk.aliaksandr.noteapp.model.Note
import by.kavalchuk.aliaksandr.noteapp.navigation.NavRoute
import by.kavalchuk.aliaksandr.noteapp.ui.theme.NoteAppTheme

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {
//    val context = LocalContext.current
//    val mainViewModel: MainViewModel =
//        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

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
//            NoteItem("Title 1", "Subtitle 1 for text", navController)
//            NoteItem("Title 2", "Subtitle 2 for text", navController)
//            NoteItem("Title 3", "Subtitle 3 for text", navController)
//            NoteItem("Title 4", "Subtitle 4 for text", navController)
//            NoteItem("Title 5", "Subtitle 5 for text", navController)
//            NoteItem("Title 6", "Subtitle 6 for text", navController)
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route)
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

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    NoteAppTheme {
        MainScreen(
            navController = rememberNavController(),
            mainViewModel = hiltViewModel()
        )
    }
}



















