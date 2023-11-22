package com.omongole.fred.composenewsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.omongole.fred.composenewsapp.R
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.ui.screens.home.HomeScreenViewModel

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
fun ErrorComposable(
    errorMessage: String,
    retry: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.sad_emoji),
                contentDescription = "sad emoji",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(64.dp)
            )
            Text( text = errorMessage, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center )
            Spacer(modifier = Modifier.size(30.dp))
            OutlinedButton(
                onClick = { retry() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 100.dp)
            ) {
                Text(text = "Retry")
            }
        }
    }
}

@Preview( showBackground = true )
@Composable
fun ErrorComposePrev() {
    ErrorComposable(errorMessage = "Error", retry = {})
}

@Composable
fun ArticleCard(
    article: Article,
    onCardClick: () -> Unit
) {
    Row(
        Modifier
            .clickable { onCardClick() }
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(96.dp)
                .clip(MaterialTheme.shapes.medium),
            model = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder)
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier= Modifier
                .padding(horizontal = 3.dp)
                .height(96.dp)
        ) {
            Text(
               text = article.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source?.name ?: "",
                    style = TextStyle(
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon( imageVector = Icons.Filled.CheckCircle, contentDescription = "")
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = article.publishedAt ?: "",
                    style = TextStyle(
                        fontWeight = FontWeight.Medium
                    ),
                    maxLines =1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
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
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ArticlesList(
    articles: LazyPagingItems<Article>,
    onArticleClick: ( Article ) -> Unit
) {
    LazyColumn() {
        items(articles){
            it?.let {
                ArticleCard(
                    article = it,
                    onCardClick = { onArticleClick(it) }
                )
            }
        }
        articles.apply {
            when (loadState.append) {
                is LoadState.Loading -> { //loading next page
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(40.dp),
                                strokeWidth = 5.dp
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                        }
                    }
                }
                is LoadState.Error -> {
                    val error = articles.loadState.append as LoadState.Error
                    item {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, end = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text( text = "${error.error.message}", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center )
                            Spacer(modifier = Modifier.size(4.dp))
                        }
                    }
                }
                else -> Unit
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchWidget(
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val query = homeScreenViewModel.uiState.value.searchQuery

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(56.dp)
            .semantics {
                contentDescription = "SearchWidget"
            },
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    contentDescription = "TextField"
                },
            value = query,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    text = "Search news..."
                )
            },
            shape = MaterialTheme.shapes.medium,
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier
                        .semantics {
                            contentDescription = "CloseButton"
                        },
                    onClick = {
                        if (query.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if ( query.isNotEmpty() ) {
                        onSearchClicked(query)
                    }
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = if( isSystemInDarkTheme() ) Color.White else Color.Black,
                cursorColor = if( isSystemInDarkTheme() ) Color.White else Color.Black,
                errorIndicatorColor = Color.Transparent
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    onBookMarkClick: () -> Unit,
    onBackClick: () -> Unit,
    onWebClick: () -> Unit,
    onShareClick: () -> Unit,
    bookMarkIcon: Int
) {
    TopAppBar(
        title = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            navigationIconContentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = { onBookMarkClick() }) {
                Icon(painterResource(id = bookMarkIcon), contentDescription = "")
            }
            IconButton(onClick = { onShareClick() }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "")
            }
            IconButton(onClick = { onWebClick() }) {
                Icon(painterResource(id = R.drawable.browse), contentDescription = "", modifier = Modifier.size(42.dp))
            }
        }

    )
}