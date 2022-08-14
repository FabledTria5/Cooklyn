package dev.fabled.splash_feature.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.fabled.common.ui.Active
import dev.fabled.common.ui.DarkPrimary
import dev.fabled.common.ui.Sansation
import dev.fabled.splash_feature.SplashViewModel

@Composable
fun SplashScreen(splashViewModel: SplashViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DrawLogo()
    }
}

@Composable
fun DrawLogo() {
    Text(
        text = buildAnnotatedString {
            append(
                AnnotatedString(
                    text = "Cook",
                    spanStyle = SpanStyle(color = DarkPrimary)
                )
            )
            append(AnnotatedString(text = "lyn", spanStyle = SpanStyle(color = Active)))
        },
        fontFamily = Sansation,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp
    )
}