package dev.fabled.common.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fabled.common.ui.Active
import dev.fabled.common.ui.DarkPrimary
import dev.fabled.common.ui.SegoeUi

@Composable
fun RecommendationCard(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.BatteryFull,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    var circleRadius by remember { mutableStateOf(0f) }

    val selectedBackgroundRadius by animateFloatAsState(
        targetValue = if (isSelected) circleRadius else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(size = 8.dp))
            .border(
                border = BorderStroke(width = 1.dp, color = Color.LightGray),
                shape = RoundedCornerShape(size = 8.dp)
            )
            .clickable(
                interactionSource = MutableInteractionSource(), indication = null, onClick = onClick
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 50.dp)
        ) {
            Canvas(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
            ) {
                circleRadius = size.width

                drawCircle(
                    color = Active, radius = if (isSelected) selectedBackgroundRadius else 0f
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = text,
                    modifier = Modifier.size(100.dp),
                    tint = DarkPrimary
                )
                Text(
                    text = text,
                    color = DarkPrimary,
                    fontFamily = SegoeUi,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationCardPreview() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 10.dp)
    ) {
        items(count = 3) {
            var isSelected by remember { mutableStateOf(false) }

            RecommendationCard(
                modifier = Modifier
                    .padding(end = if (it % 2 == 0) 10.dp else 0.dp, bottom = 10.dp)
                    .fillMaxWidth(fraction = .4f),
                onClick = { isSelected = !isSelected },
                isSelected = isSelected,
                text = "Easy"
            )
        }
    }
}