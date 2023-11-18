package com.omongole.fred.composenewsapp.ui.screens

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.ACTION_VIEW
import android.content.Intent.EXTRA_TEXT
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.omongole.fred.composenewsapp.R
import com.omongole.fred.composenewsapp.ui.components.DetailTopBar
import com.omongole.fred.composenewsapp.ui.components.TextComposable
import com.omongole.fred.composenewsapp.ui.viewModels.DetailScreenEvent
import com.omongole.fred.composenewsapp.ui.viewModels.DetailScreenViewModel
import com.omongole.fred.composenewsapp.ui.viewModels.SharedViewModel

@Composable
fun DetailScreen(
    sharedViewModel: SharedViewModel,
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel()
) {
    val article = sharedViewModel.article

    val context = LocalContext.current

    val readMoreText = buildAnnotatedString {
        withStyle( style = SpanStyle( color = Color.Blue)){
            pushStringAnnotation( tag = "Read more", annotation = "Read more")
            append( "Read more" )
        }
    }

    if ( detailScreenViewModel.sideEffect != null ) {
        Toast.makeText(LocalContext.current, detailScreenViewModel.sideEffect, Toast.LENGTH_SHORT).show()
        detailScreenViewModel.onEvent( DetailScreenEvent.RemoveSideEffect )
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        DetailTopBar(
            onBookMarkClick = {
                detailScreenViewModel.onEvent(DetailScreenEvent.SaveOrDeleteArticle(article!!))
            },
            onBackClick = {},
            onWebClick = {
                Intent(ACTION_VIEW).also {
                    it.data = Uri.parse(article?.url)
                    if ( it.resolveActivity(context.packageManager) != null ) {
                        //if there's an app for web view on phone
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(ACTION_SEND).also{
                    it.putExtra(EXTRA_TEXT, article?.url)
                    it.type = "text/plain"
                    if ( it.resolveActivity(context.packageManager) != null ) {
                        context.startActivity(it)
                    }
                }
            },
            bookMarkIcon = if ( detailScreenViewModel.articleAlreadySaved.value ) R.drawable.bookmark else R.drawable.bookmark_boader
        )
        AsyncImage(
            modifier = Modifier.height(280.dp),
            model = article?.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder)
        )
        Spacer(modifier = Modifier.size(20.dp))
        TextComposable(value = article!!.title, weight = FontWeight.Medium, size = 22.sp)
        Spacer(modifier = Modifier.size(10.dp))
        TextComposable(value = article.description ?: "")
        Spacer(modifier = Modifier.size(10.dp))
        Box( modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp), contentAlignment = Alignment.CenterEnd ) {
            ClickableText( text = readMoreText, onClick = {
                Intent(ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if ( it.resolveActivity(context.packageManager) != null ) {
                        context.startActivity(it)
                    }
                }
            })
        }
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