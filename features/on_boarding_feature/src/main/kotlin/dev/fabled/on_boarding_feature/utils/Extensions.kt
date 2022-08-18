package dev.fabled.on_boarding_feature.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 500): ContentTransform {
    return slideInHorizontally(animationSpec = tween(durationMillis = duration)) { width -> width } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutHorizontally(animationSpec = tween(durationMillis = duration)) { width -> -width } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}