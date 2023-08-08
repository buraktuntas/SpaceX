package com.burak.rocket.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.burak.rocket.data.local.entity.RocketInfo


@Composable
fun RocketListItem(
    item: RocketInfo,
    onItemClick: (RocketInfo) -> Unit,
    onFavoriteClick: () -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor =
            MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .clickable { onItemClick(item) }
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(item) }
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "Test",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape).padding(8.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1.0f)
            ) {
                Text(
                    text = item.name.toString(),
                    modifier = Modifier
                        .clip(CircleShape),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }


            Box(
                modifier = Modifier
                    .clickable { onFavoriteClick() }
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Icon(
                    if (item.isFavorite)
                        Icons.Filled.Favorite
                    else
                        Icons.Filled.FavoriteBorder, ""
                )
            }

        }
    }
}