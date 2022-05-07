package by.kavalchuk.aliaksandr.noteapp.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import by.kavalchuk.aliaksandr.noteapp.MainViewModel
import by.kavalchuk.aliaksandr.noteapp.navigation.NavRoute
import by.kavalchuk.aliaksandr.noteapp.utils.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StartScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isButtonEnable by remember { mutableStateOf(false) }

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
                        text = "LOGIN IN",
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
                        value = login,
                        onValueChange = {
                            login = it
                        },
                        label = { Text(text = Constants.Keys.LOGIN_TEXT) },
                        isError = login.isEmpty()
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .padding(top = 6.dp),
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = { Text(text = Constants.Keys.PASSWORD_TEXT) },
                        isError = password.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            LOGIN = login
                            PASSWORD = password
                            mainViewModel.initDataBase(TYPE_FIREBASE) {
                                Log.d("checkData", "Auth Firebase success")
                            }
//                                navController.popBackStack(
//                                    Constants.Screens.MAIN_SCREEN,
//                                    inclusive = false
//                                )
//                                navController.navigate(NavRoute.Main.route) {
//                                    popUpTo(MAIN_SCREEN) { saveState = true }
//                                    launchSingleTop = true
//                                }

                        },
                        enabled = login.isNotEmpty() && password.isNotEmpty()
                    ) {
                        Text(text = Constants.Keys.SIGN_IN)
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
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "What will me use?")
                Button(
                    onClick = {
                        mainViewModel.initDataBase(TYPE_ROOM) {
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
                        coroutineScope.launch {
                            bottomSheetState.show()
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

}
















