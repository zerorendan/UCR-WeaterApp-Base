package cr.ac.ucr.weather.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cr.ac.ucr.weather.R

val fonts = FontFamily(
    Font(R.font.product_sans_thin, FontWeight.Light),
    Font(R.font.product_sans_thin, FontWeight.Thin),
    Font(R.font.product_sans_bold, FontWeight.SemiBold),
    Font(R.font.product_sans_thin, FontWeight.Bold),
    Font(R.font.product_sans_regular, FontWeight.Normal),
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        color = Color.White,
        fontSize = 96.sp,
        letterSpacing = 0.sp,
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 36.sp,
        letterSpacing = 0.sp
    ),
    h3 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        fontSize = 18.sp,
        letterSpacing = 0.sp,
    ),
)