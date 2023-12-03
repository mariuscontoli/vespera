package com.absurddevs.vespera.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Material 3 color schemes

private val vesperaLightColorScheme = lightColorScheme(
    primary = vespera_light_primary,
    onPrimary = vespera_light_onPrimary,
    primaryContainer = vespera_light_primaryContainer,
    onPrimaryContainer = vespera_light_onPrimaryContainer,
    secondary = vespera_light_secondary,
    onSecondary = vespera_light_onSecondary,
    secondaryContainer = vespera_light_secondaryContainer,
    onSecondaryContainer = vespera_light_onSecondaryContainer,
    tertiary = vespera_light_tertiary,
    onTertiary = vespera_light_onTertiary,
    tertiaryContainer = vespera_light_tertiaryContainer,
    onTertiaryContainer = vespera_light_onTertiaryContainer,
    error = vespera_light_error,
    errorContainer = vespera_light_errorContainer,
    onError = vespera_light_onError,
    onErrorContainer = vespera_light_onErrorContainer,
    background = vespera_light_background,
    onBackground = vespera_light_onBackground,
    surface = vespera_light_surface,
    onSurface = vespera_light_onSurface,
    surfaceVariant = vespera_light_surfaceVariant,
    onSurfaceVariant = vespera_light_onSurfaceVariant,
    outline = vespera_light_outline,
    inverseOnSurface = vespera_light_inverseOnSurface,
    inverseSurface = vespera_light_inverseSurface,
    inversePrimary = vespera_light_inversePrimary,
    surfaceTint = vespera_light_surfaceTint,
    outlineVariant = vespera_light_outlineVariant,
    scrim = vespera_light_scrim,
)


private val vesperaDarkColorScheme = darkColorScheme(
    primary = vespera_dark_primary,
    onPrimary = vespera_dark_onPrimary,
    primaryContainer = vespera_dark_primaryContainer,
    onPrimaryContainer = vespera_dark_onPrimaryContainer,
    secondary = vespera_dark_secondary,
    onSecondary = vespera_dark_onSecondary,
    secondaryContainer = vespera_dark_secondaryContainer,
    onSecondaryContainer = vespera_dark_onSecondaryContainer,
    tertiary = vespera_dark_tertiary,
    onTertiary = vespera_dark_onTertiary,
    tertiaryContainer = vespera_dark_tertiaryContainer,
    onTertiaryContainer = vespera_dark_onTertiaryContainer,
    error = vespera_dark_error,
    errorContainer = vespera_dark_errorContainer,
    onError = vespera_dark_onError,
    onErrorContainer = vespera_dark_onErrorContainer,
    background = vespera_dark_background,
    onBackground = vespera_dark_onBackground,
    surface = vespera_dark_surface,
    onSurface = vespera_dark_onSurface,
    surfaceVariant = vespera_dark_surfaceVariant,
    onSurfaceVariant = vespera_dark_onSurfaceVariant,
    outline = vespera_dark_outline,
    inverseOnSurface = vespera_dark_inverseOnSurface,
    inverseSurface = vespera_dark_inverseSurface,
    inversePrimary = vespera_dark_inversePrimary,
    surfaceTint = vespera_dark_surfaceTint,
    outlineVariant = vespera_dark_outlineVariant,
    scrim = vespera_dark_scrim,
)

@Composable
fun VesperaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val vesperaColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> vesperaDarkColorScheme
        else -> vesperaLightColorScheme
    }

    MaterialTheme(
        colorScheme = vesperaColorScheme,
        typography = Typography,
        shapes = shapes,
        content = content
    )
}