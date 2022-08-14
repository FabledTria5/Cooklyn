package dev.fabled.on_boarding_feature.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    @StringRes val primaryText: Int,
    @StringRes val secondaryText: Int
) {



    companion object {
        fun getPages() = listOf<OnBoardingPage>()
    }
}