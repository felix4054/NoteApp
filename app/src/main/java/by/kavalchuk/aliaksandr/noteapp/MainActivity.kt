package by.kavalchuk.aliaksandr.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import by.kavalchuk.aliaksandr.noteapp.navigation.NotesNavHost
import by.kavalchuk.aliaksandr.noteapp.screens.StartScreen
import by.kavalchuk.aliaksandr.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Notes App")
                            },
//                            backgroundColor = Color.Blue,,
//                            contentColor = Color.White, //ЦВЕТ ТЕКСТА
                            elevation = 12.dp
                        )
                    },
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            NotesNavHost()
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStartScreen() {
    NoteAppTheme {
        StartScreen(
            navController = rememberNavController(),
        )
    }
}











