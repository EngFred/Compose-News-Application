package com.omongole.fred.composenewsapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        modifier = Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()
    ) {
        Box(modifier = Modifier.padding(it)) {
            BottomNavGraph(navController)
        }
    }
}

