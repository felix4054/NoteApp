package by.kavalchuk.aliaksandr.noteapp.screens

import android.app.Application
import android.content.res.Configuration
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import by.kavalchuk.aliaksandr.noteapp.MainViewModel
import by.kavalchuk.aliaksandr.noteapp.MainViewModelFactory
//import by.kavalchuk.aliaksandr.noteapp.MainViewModelFactory
import by.kavalchuk.aliaksandr.noteapp.navigation.NavRoute
import by.kavalchuk.aliaksandr.noteapp.ui.theme.NoteAppTheme
import by.kavalchuk.aliaksandr.noteapp.utils.TYPE_FIREBASE
import by.kavalchuk.aliaksandr.noteapp.utils.TYPE_ROOM
import dagger.hilt.EntryPoint


@Composable
fun StartScreen(navController: NavHostController) {
    val context = LocalContext.current
    val mainViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "What will me use?")
            Button(
                onClick = {

                    mainViewModel.initDataBase(TYPE_ROOM)
                    {
                        navController.navigate(NavRoute.Main.route)
                    }
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .width(200.dp)
            ) {
                Text(text = "Room database")
            }
            Button(
                onClick = {
                    mainViewModel.initDataBase(TYPE_FIREBASE)
                    {
                        navController.navigate(NavRoute.Main.route)
                    }
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .width(200.dp)
            ) {
                Text(text = "Firebase database")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStartScreen() {
    NoteAppTheme {
        StartScreen(
            navController = rememberNavController()
        )
    }
}















