package com.example.startwithjetpack.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.startwithjetpack.presentation.Dimens.IndicatorSize
import com.example.startwithjetpack.ui.theme.BlueGray

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = BlueGray
) {

    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize) {
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(color = if (it == selectedPage) selectedColor else unselectedColor)
            )
            Spacer(modifier = Modifier.width(2.dp))
        }
    }

}