package dev.fabled.common.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fabled.common.ui.Active

@Composable
fun PagesProgressIndicator(
    currentStep: Int,
    stepsCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 0 until stepsCount) {
            Step(
                modifier = Modifier.weight(1f),
                step = (step + 1).toString(),
                isCompete = step < currentStep,
                isCurrent = step == currentStep,
                isLast = step == stepsCount - 1,
            )
        }
    }
}

@Composable
fun Step(
    modifier: Modifier,
    step: String,
    isCompete: Boolean,
    isCurrent: Boolean,
    isLast: Boolean,
) {
    val stepColor = animateColorAsState(
        targetValue = if (isCurrent) Active else if (isCompete) Color.Black else Color.LightGray,
        animationSpec = tween(durationMillis = 500)
    )

    val stepContentColor = animateColorAsState(
        targetValue = if (!isCompete && !isCurrent) Color.LightGray else Color.Black,
        animationSpec = tween(durationMillis = 500)
    )

    val textVisibility by animateFloatAsState(
        targetValue = if (isCompete) 0f else 1f,
        animationSpec = tween(durationMillis = 500)
    )

    var activeLineWidth by remember { mutableStateOf(0f) }
    val activeLineAnimatedWidth by animateFloatAsState(
        targetValue = activeLineWidth,
        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
    )

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(vertical = 5.dp)
        ) {
            val circleWidth = size.minDimension / 2.0f
            val lineStart = center.x + circleWidth
            val lineEnd = (size.width * 1.5f) - circleWidth

            activeLineWidth = if (isCompete) lineEnd - lineStart else 0f

            drawCircle(
                color = stepColor.value,
                style = if (isCurrent) Fill else Stroke(width = 5f)
            )
            if (!isLast) {
                drawLine(
                    start = Offset(x = lineStart, y = center.y),
                    end = Offset(x = lineEnd, y = center.y),
                    color = Color.LightGray,
                    strokeWidth = 5f
                )
                drawLine(
                    start = Offset(x = lineStart, y = center.y),
                    end = Offset(
                        x = lineStart + activeLineAnimatedWidth,
                        y = center.y
                    ),
                    color = Color.Black,
                    strokeWidth = 5f
                )
            }
        }
        if (!isCompete)
            Text(
                text = step,
                modifier = Modifier.alpha(textVisibility),
                color = stepContentColor.value,
                fontWeight = FontWeight.SemiBold,
                fontSize = 21.sp
            )
        else
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.alpha(1 - textVisibility),
                tint = stepContentColor.value
            )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IndicatorPreview() {
    PagesProgressIndicator(
        stepsCount = 3,
        currentStep = 1,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )
}