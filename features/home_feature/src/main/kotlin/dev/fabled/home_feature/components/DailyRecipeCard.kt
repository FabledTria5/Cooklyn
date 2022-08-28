package dev.fabled.home_feature.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fabled.common.components.RemoteImage
import dev.fabled.common.ui.Active
import dev.fabled.common.ui.SegoeUi
import dev.fabled.domain.model.RecipeItem
import dev.fabled.home_feature.R

@ExperimentalMaterial3Api
@Composable
fun DailyRecipeCard(modifier: Modifier = Modifier, recipeItem: RecipeItem, onClick: (Int) -> Unit) {
    ElevatedCard(
        onClick = { onClick(recipeItem.recipeId) },
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
    ) {
        RemoteImage(
            imagePath = recipeItem.recipeImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            content = { painter ->
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                        contentScale = ContentScale.Crop
                    )
                    DailyRecipeCardContent(recipeItem)
                }
            }
        )
    }
}

@Composable
fun BoxScope.DailyRecipeCardContent(recipeItem: RecipeItem) {
    Row(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Schedule,
            modifier = Modifier.padding(end = 5.dp),
            contentDescription = null,
            tint = Color.White
        )
        Text(
            text = "${recipeItem.recipeTime} mins",
            color = Color.White,
            fontFamily = SegoeUi,
            fontSize = 12.sp
        )
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 30.dp)
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Active),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.dish_of_the_day),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                color = Color.Black,
                fontFamily = SegoeUi,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
        Text(
            text = recipeItem.recipeName,
            color = Color.White,
            fontFamily = SegoeUi,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}