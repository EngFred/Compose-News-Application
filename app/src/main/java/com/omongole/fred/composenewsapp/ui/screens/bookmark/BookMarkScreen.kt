package com.omongole.fred.composenewsapp.ui.screens.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.omongole.fred.composenewsapp.ui.components.ArticleCard
import com.omongole.fred.composenewsapp.ui.components.EmptyResult
import com.omongole.fred.composenewsapp.ui.navigation.Route
import com.omongole.fred.composenewsapp.ui.viewModels.SharedViewModel

@Composable
fun BookMarkScreen(
    bookMarkScreenViewModel: BookMarkScreenViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
    navController: NavHostController
) {

    val articles = bookMarkScreenViewModel.savedArticles.collectAsState().value
    
    if ( articles.isEmpty() ) {
        EmptyResult(text = "No BookMarks Yet!")
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                text = "BookMarks",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.size(17.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items( articles ){
                    ArticleCard(
                        article = it,
                        onCardClick = {
                            sharedViewModel.addArticle( it )
                            navController.navigate(Route.DetailScreen.destination)
                        }
                    )
                }
            }
        }
    }

}