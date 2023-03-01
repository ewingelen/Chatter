package com.ewingelen.chatter.core.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.ewingelen.chatter.R

private val googleFontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private fun getGoogleFontFamily(
    name: String,
    provider: GoogleFont.Provider = googleFontProvider,
    weights: List<FontWeight>
) = FontFamily (
    weights.map {
        Font(GoogleFont(name), provider, it)
    }
)

private val PlusJakartaSansFontFamily = getGoogleFontFamily(
    name = "Plus Jakarta Sans",
    weights = listOf(
        FontWeight.Normal,
        FontWeight.Medium,
        FontWeight.SemiBold,
    )
)

val Typography = Typography(
    defaultFontFamily = PlusJakartaSansFontFamily,
    h1 = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    body1 = TextStyle(
        fontSize = 16.sp,
    ),
    body2 = TextStyle(
        fontSize = 16.sp,
        color = Gray900
    ),
    button = TextStyle(
        fontWeight = FontWeight.Medium
    ),
)
