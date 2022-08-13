package dev.fabled.common.ui

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import dev.fabled.cooklyn.R

val SegoeUi = FontFamily(
    Font(resId = R.font.segoe_ui_regular),
    Font(resId = R.font.segoe_ui_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.segoe_ui_bold, weight = FontWeight.Bold)
)

val Sansation = FontFamily(
    Font(resId = R.font.sansation_bold, weight = FontWeight.Bold)
)