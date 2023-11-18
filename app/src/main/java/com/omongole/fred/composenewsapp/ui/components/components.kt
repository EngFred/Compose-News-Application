package com.omongole.fred.composenewsapp.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.omongole.fred.composenewsapp.R
import com.omongole.fred.composenewsapp.data.modal.Article
import com.omongole.fred.composenewsapp.ui.theme.ComposeNewsAppTheme
import com.omongole.fred.composenewsapp.ui.theme.Purple40
import com.omongole.fred.composenewsapp.ui.viewModels.MainViewModel

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
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black
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

@Preview( showBackground = true )
@Preview( showBackground = true, uiMode = UI_MODE_NIGHT_YES )
@Composable
fun ArticleCardPreview() {
    ComposeNewsAppTheme {
        ArticleCard(article = Article(
            source = null,
            author = null,
            title = "News title",
            description = null,
            urlToImage = null,
            publishedAt = "2hrs",
            url = null
        )) {
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
            color = Purple40
        )
    }
}

@Composable
fun ArticlesList(
    articles: LazyPagingItems<Article>,
    onClick: ( Article ) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items( articles ) {
            it?.let {
                ArticleCard(
                    article = it,
                    onCardClick = { onClick(it) }
                )
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
    val mainViewModel: MainViewModel = hiltViewModel()
    val query = mainViewModel.uiState.value.searchQuery

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
                textColor = Color.Black,
                cursorColor = Color.Black,
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
            actionIconContentColor = Color.Black,
            navigationIconContentColor = Color.Black
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