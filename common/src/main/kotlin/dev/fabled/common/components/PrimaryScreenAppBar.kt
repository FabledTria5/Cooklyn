package dev.fabled.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fabled.common.ui.DarkPrimary
import dev.fabled.common.ui.SegoeUi

@Composable
fun PrimaryScreenAppBar(primaryText: String, secondaryText: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = primaryText,
            color = DarkPrimary,
            fontFamily = SegoeUi,
            fontWeight = FontWeight.Bold,
            fontSize = 38.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = secondaryText, color = Color.Gray, fontFamily = SegoeUi, fontSize = 16.sp)
    }
}