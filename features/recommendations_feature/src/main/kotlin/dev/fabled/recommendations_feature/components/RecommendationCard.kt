package dev.fabled.recommendations_feature.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fabled.common.ui.Active
import dev.fabled.common.ui.DarkPrimary
import dev.fabled.common.ui.SegoeUi
import dev.fabled.domain.model.FilterItem

@Composable
fun RecommendationCard(
    modifier: Modifier = Modifier,
    filterItem: FilterItem,
    onClick: () -> Unit
) {
    var activeBoxHeight by remember { mutableStateOf(0f) }
    val activeBoxRadius by animateFloatAsState(
        targetValue = if (filterItem.isSelected) activeBoxHeight else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(15.dp))
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                onClick()
            }
            .onGloballyPositioned {
                activeBoxHeight = it.size.height.toFloat()
            },
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(color = Active, radius = activeBoxRadius)
        }

        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = filterItem.image),
                contentDescription = "",
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(100.dp),
                tint = Color.Unspecified
            )
            Text(
                text = stringResource(id = filterItem.name),
                color = DarkPrimary,
                fontFamily = SegoeUi,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}