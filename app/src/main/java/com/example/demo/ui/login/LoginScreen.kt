package com.example.demo.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.demo.navigation.NavigationItem
import kotlinx.serialization.Serializable

@Serializable
data class HomeRoute(
    val userName: String
)


@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel) {
    val context = LocalContext.current
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
            if (value.isNotEmpty()) {
                viewModel.insertUser(value)
                navController.navigate(NavigationItem.Home.route)
            } else {
                Toast.makeText(context, "Enter username", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Login")
        }
    }
}