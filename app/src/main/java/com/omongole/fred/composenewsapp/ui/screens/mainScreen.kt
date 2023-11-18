package com.omongole.fred.composenewsapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.omongole.fred.composenewsapp.ui.components.bottomNavigation.BottomBar
import com.omongole.fred.composenewsapp.ui.navigation.BottomNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar( navController = navController ) },
    ) {
        BottomNavGraph(navController)
    }
}

