package app.trian.driver.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.trian.component.ui.theme.*

private val DarkColorPalette = darkColors(
    primary = Primary,
    secondary = Secondary,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onBackground = OnBackgroundDark,
    onSurface = OnSurfaceDark,
)

private val LightColorPalette = lightColors(
    primary = Primary,
    secondary = Secondary,
    background = Background,
    surface = SurfaceLight,
    onPrimary = BlueJade,
    onSecondary = TextInactive,
    onBackground = TextActive,
    onSurface = OnSurfaceLight,

)

@Composable
fun CexupTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

