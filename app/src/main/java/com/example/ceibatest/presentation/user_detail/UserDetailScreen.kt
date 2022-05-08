package com.example.ceibatest.presentation.user_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ceibatest.presentation.ui.theme.CeibaGreen

@Composable
fun UserDetailScreen(
    navController: NavController,
    viewModel: UserDetailViewModel = hiltViewModel()

) {
    val user = viewModel.state.value.user
    val posts = viewModel.state2.value.post
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)) {

        items(user) { user ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                elevation = 5.dp
            ) {
                Column(Modifier.padding(10.dp)) {
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
                }
            }

        }
        items(posts) { post ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                elevation = 5.dp
            ) {
                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = post.title,
                        color = CeibaGreen,
                        fontWeight = FontWeight.Black,
                        fontSize = 22.sp
                    )
                    Text(text = post.body)

                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }


    }

}