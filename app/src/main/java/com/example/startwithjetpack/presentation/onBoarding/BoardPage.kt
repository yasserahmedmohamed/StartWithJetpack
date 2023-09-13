package com.example.startwithjetpack.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.example.startwithjetpack.R

data class BoardPage(
    val title:String,
    val description:String,
    @DrawableRes val image:Int
)




val pages = listOf(
    BoardPage(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding1
    ),
    BoardPage(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding2
    ),
    BoardPage(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding3
    )
)
