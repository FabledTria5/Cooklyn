package dev.fabled.recommendations_feature.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.fabled.domain.model.FilterItem
import dev.fabled.recommendations_feature.components.RecommendationCard

@Composable
fun RecommendationsPage(
    modifier: Modifier = Modifier,
    recommendations: List<FilterItem>,
    onItemClick: (FilterItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        itemsIndexed(items = recommendations) { index, filter ->
            RecommendationCard(
                filterItem = filter,
                onClick = { onItemClick(filter) },
                modifier = Modifier.padding(
                    bottom = 15.dp,
                    end = if (index % 2 == 0) 10.dp else 0.dp
                )
            )
        }
    }
}