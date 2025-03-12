package com.example.rewetask.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rewetask.model.LocalCityDetail
import com.example.rewetask.ui.searchcity.CityListViewState

@Composable
fun CityList(
    onNavigateToDetail: (LocalCityDetail) -> Unit,
    onBookmarkClick: (LocalCityDetail) -> Unit,
    viewState: CityListViewState.CityList,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(viewState.cities) { city ->
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .clickable { onNavigateToDetail(city) },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = city.name,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = " ${city.countryName}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer( Modifier.weight(1f).fillMaxHeight() )
                IconButton(
                    onClick = { onBookmarkClick(city) },
                ) {
                    Icon(
                        imageVector = if (city.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark City",
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CitiesPreview() {
    CityList(
        onNavigateToDetail = {},
        onBookmarkClick = {},
        viewState = CityListViewState.CityList(
            listOf(
                LocalCityDetail(
                    name = "Linz",
                    countryName = "Austria",
                    isBookmarked = false,
                ),
                LocalCityDetail(
                    name = "Graz",
                    countryName = "Austria",
                    isBookmarked = true,
                ),
                LocalCityDetail(
                    name = "Innsbruck",
                    countryName = "Austria",
                    isBookmarked = false,
                )
            )
        ),
    )
}
