package com.example.messageappebcom.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.messageappebcom.R

// Set of Material typography styles to start with
val fonts = FontFamily(
    Font(R.font.yekan_bakh),
)
val fontNum = FontFamily(
    Font(R.font.yekan_bakh_fanum)
)
val Typography =
    Typography(
        //title style
        body1 = TextStyle(
            fontFamily = fonts,
            fontSize = 16.sp,
            lineHeight = 20.8.sp
        ),
        //desc style
        subtitle1 = TextStyle(
            fontFamily = fonts,
            fontSize = 14.sp,
            lineHeight = 23.8.sp
        ),
        //number style
        subtitle2 = TextStyle(
            fontFamily = fontNum,
            fontSize = 14.sp,
            lineHeight = 18.2.sp
        ),
        //text top app bar
        h6 = TextStyle(
            fontFamily = fonts,
            fontSize = 20.sp,
            lineHeight = 20.sp
        ),
        //tab button
        button = TextStyle(
            fontFamily = fonts,
            fontSize = 14.sp,
            lineHeight = 14.sp
        )


        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
    )





