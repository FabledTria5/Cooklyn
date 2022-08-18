package dev.fabled.on_boarding_feature.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.fabled.on_boarding_feature.R

sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    @StringRes val primaryText: Int,
    @StringRes val secondaryText: Int
) {

    object First : OnBoardingPage(
        image = R.drawable.on_boarding_1,
        primaryText = R.string.on_boarding_primary_1,
        secondaryText = R.string.on_boarding_secondary_1
    )

    object Second : OnBoardingPage(
        image = R.drawable.on_boarding_2,
        primaryText = R.string.on_boarding_primary_2,
        secondaryText = R.string.on_boarding_secondary_2
    )

    object Third : OnBoardingPage(
        image = R.drawable.on_boarding_3,
        primaryText = R.string.on_boarding_primary_3,
        secondaryText = R.string.on_boarding_secondary_3
    )

    object Fourth : OnBoardingPage(
        image = R.drawable.on_boarding_4,
        primaryText = R.string.on_boarding_primary_4,
        secondaryText = R.string.On_boarding_secondary_4
    )

    companion object {
        fun getPages() = listOf(First, Second, Third, Fourth)
    }
}