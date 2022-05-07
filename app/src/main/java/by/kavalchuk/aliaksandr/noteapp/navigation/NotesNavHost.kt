package by.kavalchuk.aliaksandr.noteapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import by.kavalchuk.aliaksandr.noteapp.MainViewModel
import by.kavalchuk.aliaksandr.noteapp.screens.AddScreen
import by.kavalchuk.aliaksandr.noteapp.screens.MainScreen
import by.kavalchuk.aliaksandr.noteapp.screens.NoteScreen
import by.kavalchuk.aliaksandr.noteapp.screens.StartScreen
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Keys.ID
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Screens.ADD_SCREEN
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Screens.MAIN_SCREEN
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Screens.NOTE_SCREEN
import by.kavalchuk.aliaksandr.noteapp.utils.Constants.Screens.START_SCREEN

sealed class NavRoute(val route: String) {
    object Start : NavRoute(route = START_SCREEN)
    object Main : NavRoute(route = MAIN_SCREEN)
    object Add : NavRoute(route = ADD_SCREEN)
    object Note : NavRoute(route = NOTE_SCREEN)

}

@Composable
fun NotesNavHost(mainViewModel: MainViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Start.route,
    ) {
        composable(NavRoute.Start.route) {
            Log.e("composable", " Start Screen")
            StartScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }
        composable(NavRoute.Main.route) {
            Log.e("composable", " Main Screen")
            MainScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }
        composable(NavRoute.Add.route) {
            Log.e("composable", " Add Screen")
            AddScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }
        composable(NavRoute.Note.route + "/{${ID}}") { backStackEntry ->
            Log.e("composable", " Note Screen" + "/{${ID}}")
            NoteScreen(
                navController = navController,
                mainViewModel = mainViewModel,
                noteId = backStackEntry.arguments?.getString(ID)
            )
        }
    }
}













