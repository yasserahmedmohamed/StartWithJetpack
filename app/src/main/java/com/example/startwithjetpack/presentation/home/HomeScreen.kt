package com.example.startwithjetpack.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.startwithjetpack.R
import com.example.startwithjetpack.domain.model.Article
import com.example.startwithjetpack.presentation.Dimens.MediumPadding1
import com.example.startwithjetpack.presentation.common.ArticlesList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToDetails: (Article) -> Unit
) {

    var searchText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = MediumPadding1),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(30.dp)
            )

            Text(
                text = "Top HeadLines",

                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Medium),
                color = colorResource(
                    id = R.color.text_title
                )
            )
        }
//        Spacer(modifier = Modifier.height(MediumPadding1))
//
//        SearchBar(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    top = MediumPadding1,
//                    start = MediumPadding1,
//                    end = MediumPadding1
//                ),
//            text = searchText,
//            readOnly = true,
//            onClick = {
//            }
//        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        // add drop down country here
        ArticlesList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding1),
            articles = articles,
            onItemClick = {
                navigateToDetails(it)
            }
        )
    }

}


@Preview
@Composable
fun HomeScreenPreview() {

}