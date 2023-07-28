package com.example.primierleaguematches.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.primierleaguematches.R

// Set of Material typography styles to start with
val robotoFamily = FontFamily(
    listOf(
        Font(R.font.roboto_regular, FontWeight(1)),
        Font(R.font.roboto_black, FontWeight(1)),
        Font(R.font.roboto_bold, FontWeight(1))
    )
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = TextWhite
    ),
    titleLarge = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = TextWhite

    ),
    bodySmall =TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Bold,
        fontSize =12.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = TextWhite

    ),
    labelSmall = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = TextWhite

    )

)