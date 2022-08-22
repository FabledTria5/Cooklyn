package dev.fabled.common.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.tween

@ExperimentalAnimationApi
fun textFadeAnimation(duration: Int = 500): ContentTransform {
    return fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}

@ExperimentalAnimationApi
fun textSlideAnimation(duration: Int = 500): ContentTransform {
    return slideInHorizontally(animationSpec = tween(durationMillis = duration)) { width -> width } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutHorizontally(animationSpec = tween(durationMillis = duration)) { width -> -width } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}