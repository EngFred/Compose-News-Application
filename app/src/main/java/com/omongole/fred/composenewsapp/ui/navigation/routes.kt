package com.omongole.fred.composenewsapp.ui.navigation

sealed class Route( val destination: String ) {
    object HomeScreen : Route("home")
}