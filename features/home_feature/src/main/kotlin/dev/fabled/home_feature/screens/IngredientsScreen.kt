package dev.fabled.home_feature.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fabled.common.components.RemoteImage
import dev.fabled.common.ui.DarkPrimary
import dev.fabled.common.ui.SegoeUi
import dev.fabled.domain.model.IngredientItem
import dev.fabled.home_feature.HomeViewModel
import dev.fabled.home_feature.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun IngredientsScreen(homeViewModel: HomeViewModel) {
    val ingredientsList = homeViewModel.ingredientsList

    Scaffold(
        topBar = { TopBar(onBackClicked = homeViewModel::onIngredientsBackClicked) },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 15.dp,
                top = paddingValues.calculateTopPadding(),
                end = 15.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
        ) {
            itemsIndexed(
                items = ingredientsList,
                key = { _, item -> item.id }
            ) { index, item ->
                IngredientsListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItemPlacement(),
                    ingredientItem = item,
                    onAddIngredientClicked = homeViewModel::onIngredientSelected
                )
                if (index != ingredientsList.lastIndex) {
                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.LightGray.copy(alpha = .5f))
                    )
                }
            }
        }
    }
}

@Composable
fun IngredientsListItem(
    modifier: Modifier = Modifier,
    ingredientItem: IngredientItem,
    onAddIngredientClicked: (IngredientItem) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RemoteImage(
                imagePath = ingredientItem.image,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(50.dp)
            )
            Text(
                text = ingredientItem.name,
                color = DarkPrimary,
                fontFamily = SegoeUi,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        IconButton(onClick = { onAddIngredientClicked(ingredientItem) }) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = stringResource(R.string.icon_add_ingredient),
                tint = DarkPrimary
            )
        }
    }
}

@Composable
fun TopBar(onBackClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 10.dp, horizontal = 10.dp),
    ) {
        IconButton(onClick = onBackClicked, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = stringResource(R.string.button_back),
                tint = DarkPrimary
            )
        }
        Text(
            text = stringResource(R.string.your_ingredients),
            modifier = Modifier.align(Alignment.Center),
            color = DarkPrimary,
            fontFamily = SegoeUi,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp
        )
    }
}