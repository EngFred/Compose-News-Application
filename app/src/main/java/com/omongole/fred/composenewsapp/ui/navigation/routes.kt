package com.omongole.fred.composenewsapp.ui.navigation

sealed class Route( val destination: String ) {
    object HomeScreen : Route("home")
    object DetailScreen : Route("detail")
    object SearchScreen : Route("search")
    object BookmarkScreen : Route("bookmark")
    object NewsNavigatorScreen : Route("news")
}