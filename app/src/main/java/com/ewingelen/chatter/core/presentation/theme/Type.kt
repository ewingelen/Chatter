package com.ewingelen.chatter.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ewingelen.chatter.R

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
private val googleFontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private fun getGoogleFontFamily(
    name: String,
    provider: GoogleFont.Provider = googleFontProvider,
    weights: List<FontWeight>,
) = FontFamily(
    weights.map {
        Font(GoogleFont(name), provider, it)
    }
)

private val LatoFontFamily = getGoogleFontFamily(
    name = "Lato",
    weights = listOf(
        FontWeight.Normal,
        FontWeight.Medium,
        FontWeight.Bold,
    )
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = LatoFontFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    ),
    headlineMedium = TextStyle(
        fontFamily = LatoFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
    ),
    bodyLarge = TextStyle(
        fontFamily = LatoFontFamily,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = LatoFontFamily,
        fontSize = 16.sp,
        color = Gray900
    ),
    labelLarge = TextStyle(
        fontFamily = LatoFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    labelMedium = TextStyle(
        fontSize = 14.sp,
        color = Gray900
    ),
)
