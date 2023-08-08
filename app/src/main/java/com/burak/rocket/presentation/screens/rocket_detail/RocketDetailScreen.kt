package com.burak.rocket.presentation.screens.rocket_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.burak.rocket.data.local.entity.RocketInfo
import com.burak.rocket.presentation.screens.rocket.RocketListUiEvent
import com.burak.rocket.presentation.screens.rocket_detail.components.ImageSlider
import com.burak.rocket.util.UiEvent
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RocketDetailScreen(
    item:RocketInfo,
    uiEventFlow: Flow<UiEvent>,
    onUiEvent: (UiEvent) -> Unit,
    onEvent: (RocketListUiEvent) -> Unit,
) {

    val selected = remember { mutableStateOf(item.isFavorite) }

    LaunchedEffect(false) {
        uiEventFlow.collect { event ->
            onUiEvent(event)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                4.dp
            ),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ImageSlider(imageList = item.sliderListUrl)


        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = item.name.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.description.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                if (selected.value)
                    Icons.Filled.Favorite
                else
                    Icons.Filled.FavoriteBorder, "",
                modifier = Modifier
                    .clickable {
                        onEvent(
                            RocketListUiEvent.OnFavoriteClick(
                                item = item
                            )
                        )
                        selected.value = !selected.value
                    }

            )
        }
    }
}