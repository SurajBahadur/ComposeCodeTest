package com.example.demo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.demo.ui.detail.DetailScreen
import com.example.demo.ui.home.HomeScreen
import com.example.demo.ui.home.MedicineDetailRoute
import com.example.demo.ui.home.model.CustomNavType
import com.example.demo.ui.home.model.MedicineData
import com.example.demo.ui.login.HomeRoute
import com.example.demo.ui.login.LoginScreen
import kotlin.reflect.typeOf


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Login.route) {
            LoginScreen(navController, hiltViewModel())
        }
        /*composable<HomeRoute> {
            val arguments = it.toRoute<HomeRoute>()
            HomeScreen(navController, hiltViewModel(), arguments.userName)
        }*/

        composable(NavigationItem.Home.route) {
            HomeScreen(navController,hiltViewModel())
        }

        composable<MedicineDetailRoute>(
            typeMap = mapOf(
                typeOf<MedicineData>() to CustomNavType.DogType
            )
        ) {
            val arguments = it.toRoute<MedicineDetailRoute>()
            DetailScreen(arguments.medicineData)
        }
    }
}