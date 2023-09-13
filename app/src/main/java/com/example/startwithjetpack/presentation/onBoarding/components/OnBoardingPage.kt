package com.example.startwithjetpack.presentation.onBoarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.startwithjetpack.R
import com.example.startwithjetpack.presentation.Dimens.MediumPadding1
import com.example.startwithjetpack.presentation.onBoarding.BoardPage
import com.example.startwithjetpack.presentation.onBoarding.pages
import com.example.startwithjetpack.ui.theme.StartWithJetpackTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: BoardPage
) {

    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = page.image),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f),
            contentScale = ContentScale.Crop,
            contentDescription = "page image"
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MediumPadding1),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MediumPadding1),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun OnBoardingPagePreview() {
    StartWithJetpackTheme {
        OnBoardingPage(page = pages[0])
    }
}