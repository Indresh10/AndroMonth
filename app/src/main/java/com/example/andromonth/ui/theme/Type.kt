package com.example.andromonth.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.andromonth.R

val Righteous = FontFamily(Font(R.font.righteous))
val Arvo = FontFamily(Font(R.font.arvo_bold, FontWeight.Bold), Font(R.font.arvo_regular,FontWeight.Normal))
val Monserrat = FontFamily(Font(R.font.montserrat_light))

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Monserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    displayLarge = TextStyle(
        fontFamily = Righteous,
        fontSize = 32.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Arvo,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Arvo,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)