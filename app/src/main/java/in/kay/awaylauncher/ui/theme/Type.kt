package `in`.kay.awaylauncher.ui.theme

import `in`.kay.awaylauncher.R
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = gilroy(),
        fontWeight = FontWeight.SemiBold,
        fontSize = 40.sp,
        color = colorWhite
    ),
    body1= TextStyle(
        fontFamily = gilroy(),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = colorWhite
    ),
    body2 = TextStyle(
        fontFamily = gilroy(),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = colorWhite
    ),
    defaultFontFamily = gilroy()
)


fun gilroy() = FontFamily(
    Font(R.font.font_gilroy_bold, FontWeight.Bold),
    Font(R.font.font_gilroy_medium, FontWeight.Medium),
    Font(R.font.font_gilroy_semi_bold, FontWeight.SemiBold),
)