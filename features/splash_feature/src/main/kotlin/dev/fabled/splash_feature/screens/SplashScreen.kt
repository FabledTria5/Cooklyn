package dev.fabled.splash_feature.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.fabled.common.components.DrawLogo
import dev.fabled.splash_feature.SplashViewModel

@Composable
fun SplashScreen(splashViewModel: SplashViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DrawLogo()
    }
}