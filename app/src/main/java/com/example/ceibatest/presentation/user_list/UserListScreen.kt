package com.example.ceibatest.presentation.user_list

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ceibatest.domain.model.User
import com.example.ceibatest.presentation.Screen
import com.example.ceibatest.presentation.ui.theme.CeibaBackground
import com.example.ceibatest.presentation.ui.theme.CeibaGreen

@Composable
fun UserListScreen(
    navController: NavController,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val localContext = LocalContext.current
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val localFocusManager = LocalFocusManager.current
    val state = viewModel.state.value
    var backKey = 0
    var mToast: Toast? = null


    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(CeibaBackground)
            .padding(horizontal = 20.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {
        item {
            SearchView(textState)
            Spacer(modifier = Modifier.height(20.dp))
        }
        if (state.isLoading) {
            item {
                Dialog(
                    onDismissRequest = { },
                    DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                ) {
                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier
                            .size(150.dp)
                            .background(
                                White, shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(color = CeibaGreen)
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(text = "Por favor espere...")
                        }
                    }
                }
            }
        } else if (!state.isLoading && state.error.isEmpty()) {
            if (textState.value.text.isBlank()) {
                items(state.user) { user ->
                    UserCard(user, navController)
                }
            } else {
                val userList = mutableListOf<User>()
                state.user.forEach { user ->
                    if (user.name.lowercase().contains(textState.value.text.lowercase())) {
                        userList.add(user)
                    }
                }
                items(userList) { user ->
                    UserCard(user, navController)
                }
                if (userList.size == 0 && textState.value.text.length + 1 > backKey) {

                    mToast?.cancel()
                    mToast = Toast.makeText(localContext, "List is empty", Toast.LENGTH_SHORT)
                    mToast?.setGravity(Gravity.CENTER, 0, 0)
                    mToast?.show()
                }

                backKey = textState.value.text.length

            }
        } else if (state.error.isNotEmpty()) {
            item {
                Text(text = state.error)
            }
        }
    }
}

@Composable
private fun UserCard(user: User, navController: NavController) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        elevation = 5.dp
    ) {
        Column(Modifier.padding(15.dp)) {
            Text(
                text = user.name,
                color = CeibaGreen,
                fontWeight = FontWeight.Black,
                fontSize = 22.sp
            )
            Row {
                Icon(Icons.Rounded.Call, contentDescription = null, tint = CeibaGreen)
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = user.phone)
            }
            Row {
                Icon(Icons.Rounded.Email, contentDescription = null, tint = CeibaGreen)
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = user.email)

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                ClickableText(
                    text = AnnotatedString("VER PUBLICACIONES"),
                    style = TextStyle(color = CeibaGreen, fontWeight = FontWeight.Black),
                    onClick = {
                        navController.navigate(Screen.UserDetailScreen.route + "/${user.id}")
                    })
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}