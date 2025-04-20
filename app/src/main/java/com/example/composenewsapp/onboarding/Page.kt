package com.example.e_pharmacycompose.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.composenewsapp.R

data class Page(
    val title:String,
    val description:String,
    @DrawableRes val image:Int
)

val pages = listOf(
    Page(
        "Lorum ispum simply text1",
        "Lorum ispum simply dummy text of the printer of the year",
        R.drawable.onboarding1
    ),
    Page(
        "Lorum ispum simply text2",
        "Lorum ispum simply dummy text of the printer of the year",
        R.drawable.onboarding2
    ),
    Page(
        "Lorum ispum simply text3",
        "Lorum ispum simply dummy text of the printer of the year",
        R.drawable.onboarding3
    ),

)
