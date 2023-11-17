package com.omongole.fred.composenewsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.omongole.fred.composenewsapp.R
import com.omongole.fred.composenewsapp.ui.components.TextComposable
import com.omongole.fred.composenewsapp.ui.viewModel.SharedViewModel

@Composable
fun DetailScreen(
    sharedViewModel: SharedViewModel
) {
    val article = sharedViewModel.article

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        AsyncImage(
            modifier = Modifier.height(270.dp),
            model = article?.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder)
        )
        Spacer(modifier = Modifier.size(20.dp))
        TextComposable(value = article?.title ?: "", weight = FontWeight.Medium, size = 22.sp)
        Spacer(modifier = Modifier.size(10.dp))
        TextComposable(value = article?.description ?: "")
        Spacer(modifier = Modifier.weight(1f))
        Row(
            Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .width(180.dp)
                    .padding(8.dp),
                text = article?.author ?: "",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 1
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .width(130.dp)
                    .padding(8.dp),
                text = article?.source?.name ?: "",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 1
            )
        }
    }
}