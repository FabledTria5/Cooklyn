package dev.fabled.common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import dev.fabled.common.R

@Composable
fun RemoteImage(
    modifier: Modifier = Modifier,
    imagePath: String?,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    content: @Composable (Painter) -> Unit
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imagePath)
        .crossfade(enable = true)
        .placeholder(R.drawable.placeholder)
        .build()

    SubcomposeAsyncImage(
        model = imageRequest,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    ) {
        content(painter)
    }
}

@Composable
fun RemoteImage(
    modifier: Modifier = Modifier,
    imagePath: String?,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imagePath)
        .crossfade(enable = true)
        .placeholder(R.drawable.placeholder)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}