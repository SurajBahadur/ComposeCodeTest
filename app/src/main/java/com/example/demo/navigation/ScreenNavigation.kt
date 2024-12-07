package com.example.demo.navigation


enum class Screen {
    LOGIN,
    HOME,
    DETAIL_SCREEN

}
sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object Login : NavigationItem(Screen.LOGIN.name)
    data object DetailScreen : NavigationItem(Screen.DETAIL_SCREEN.name)
}

