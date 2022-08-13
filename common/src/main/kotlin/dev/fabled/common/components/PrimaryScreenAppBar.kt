package dev.fabled.common.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
        Text(text = secondaryText, color = Color.LightGray, fontFamily = SegoeUi, fontSize = 16.sp)
    }
}