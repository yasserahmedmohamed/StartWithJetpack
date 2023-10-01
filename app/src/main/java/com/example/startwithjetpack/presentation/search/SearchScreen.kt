package com.example.startwithjetpack.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.startwithjetpack.R
import com.example.startwithjetpack.domain.model.Article
import com.example.startwithjetpack.navgraph.Route
import com.example.startwithjetpack.presentation.Dimens.MediumPadding1
import com.example.startwithjetpack.presentation.common.ArticlesList
import com.example.startwithjetpack.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state:SearchState,
    event: (SearchEvent)->Unit,
    navigateToDetails:(Article)->Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null)

            Text(
                text = "Search News",

                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Medium),
                color = colorResource(
                    id = R.color.text_title
                )
            )
        }
        Spacer(modifier = Modifier.height(MediumPadding1))

        SearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            text = state.searchQuery,
            readOnly = false,
           onTextChanged = {
               event(SearchEvent.UpdateSearchQuery(it))
           },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        state.articles?.let {
            ArticlesList(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MediumPadding1),
                articles = it.collectAsLazyPagingItems(),
                onItemClick = {
                    navigateToDetails(it)
                }
            )
        }

    }
}