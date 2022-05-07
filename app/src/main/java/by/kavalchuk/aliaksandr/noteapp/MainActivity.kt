package by.kavalchuk.aliaksandr.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import by.kavalchuk.aliaksandr.noteapp.navigation.NavRoute
import by.kavalchuk.aliaksandr.noteapp.navigation.NotesNavHost
import by.kavalchuk.aliaksandr.noteapp.ui.theme.NoteAppTheme
import by.kavalchuk.aliaksandr.noteapp.utils.Constants
import by.kavalchuk.aliaksandr.noteapp.utils.DB_TYPE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                val mainViewModel = hiltViewModel<MainViewModel>()
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = "Notes App")
                                    if (DB_TYPE.value.isNotEmpty()) {
                                        Icon(
                                            imageVector = Icons.Default.ExitToApp,
                                            contentDescription = "",
                                            modifier = Modifier
                                                .clickable {
                                                    mainViewModel.signOut {
                                                        navController.navigate(NavRoute.Start.route) {
                                                            popUpTo(NavRoute.Start.route) {
                                                                inclusive = true
                                                            }
                                                        }
                                                    }
                                                }
                                        )
                                    }
                                }
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
                            NotesNavHost(mainViewModel, navController)
                        }
                    }
                )
            }
        }
    }
}











