package com.burak.rocket.presentation.screens.rocket_detail.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@ExperimentalPagerApi
@Composable
fun ImageSlider(
  imageList: List<String?>,
) {
  val pagerState = rememberPagerState(imageList.size)

  LaunchedEffect(key1 = pagerState.currentPage) {
    delay(3000)
    var newPosition = pagerState.currentPage + 1
    if (newPosition > imageList.size - 1) {
      newPosition = 0
    }
    pagerState.animateScrollToPage(newPosition)
  }

  Column(
    modifier = Modifier
      .fillMaxWidth(),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    HorizontalPager(
      modifier = Modifier
        .fillMaxWidth()
        .height(130.dp)
        .background(
          color = Color.White,
          shape = MaterialTheme.shapes.small
        ),
      state = pagerState,
      verticalAlignment = Alignment.Top,
    ) {
      AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
          .data(imageList)
          .crossfade(true)
          .build(),
        contentDescription = "Test",
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape)
      )

    LazyRow(
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
      horizontalArrangement = Arrangement.Center
    ) {

      items(imageList.size) { index ->
        if (index == pagerState.currentPage) {
          Box(
            modifier = Modifier
              .size(4.dp)
              .clip(CircleShape)
              .background(color = MaterialTheme.colors.onBackground)
          )
        } else {
          Box(
            modifier = Modifier
              .size(4.dp)
              .clip(CircleShape)
              .background(color = Color.Gray)
          )
        }

        if (index != imageList.size - 1) {
          Spacer(
            modifier = Modifier
              .padding(2.dp)
          )
        }
      }
    }
    }
}
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewBannerSlider() {
  ImageSlider(
    imageList = arrayListOf()
  )
}