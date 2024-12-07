package com.example.demo.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

@Serializable
data class HomeRoute(
    val userName: String
)


@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var value by remember { mutableStateOf("") }
        TextField(
            value = value,
            onValueChange = { value = it },
            label = { Text("Enter User Name") },
            maxLines = 1,
            modifier = Modifier.padding(20.dp)
        )
        Button(onClick = {
            viewModel.insertUser(value)
            navController.navigate(HomeRoute(value))
        }) {
            Text("Login")
        }
    }
}