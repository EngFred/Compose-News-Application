package com.omongole.fred.composenewsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.omongole.fred.composenewsapp.R
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.data.modal.NewsApiResponse
import com.omongole.fred.composenewsapp.ui.theme.Purple40


@Composable
fun TextComposable(
    value: String,
    size: TextUnit = 18.sp,
    weight: FontWeight = FontWeight.Normal
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = value,
        style = TextStyle(
            fontSize = size,
            fontWeight = weight,
        )

    )
}

@Composable
fun PagerArticleComponent( it: Int, article: Article ) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        AsyncImage(
            modifier = Modifier.height(270.dp),
            model = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder)
        ) 
        Spacer(modifier = Modifier.size(20.dp))
        TextComposable(value = article.title ?: "", weight = FontWeight.Medium, size = 22.sp)
        Spacer(modifier = Modifier.size(10.dp))
        TextComposable(value = article.description ?: "")
        Spacer(modifier = Modifier.weight(1f))
        Row(
            Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .width(180.dp)
                    .padding(8.dp),
                text = article.author ?: "",
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
                text = article.source?.name ?: "",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 1
            )
        }
    }
}

@Composable
fun ErrorComposable() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.wrong),
            contentDescription = ""
        )
    }
}

@Composable
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            color = Purple40
        )
    }
}

@Composable
fun ArticlesList(articles: List<Article>) {
    LazyColumn() {
        items( articles) {
            TextComposable(value = it.title ?: "")
        }
    }
}