package com.absurddevs.vespera.core.designsystem.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.emptyPreferences
import com.absurddevs.vespera.core.datastore.DataStoreKeys
import com.absurddevs.vespera.core.datastore.dataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.jetbrains.annotations.VisibleForTesting
import java.io.IOException

/**
 * Light default theme color scheme.
 */
@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    primary = vespera_theme_light_primary,
    onPrimary = vespera_theme_light_onPrimary,
    primaryContainer = vespera_theme_light_primaryContainer,
    onPrimaryContainer = vespera_theme_light_onPrimaryContainer,
    secondary = vespera_theme_light_secondary,
    onSecondary = vespera_theme_light_onSecondary,
    secondaryContainer = vespera_theme_light_secondaryContainer,
    onSecondaryContainer = vespera_theme_light_onSecondaryContainer,
    tertiary = vespera_theme_light_tertiary,
    onTertiary = vespera_theme_light_onTertiary,
    tertiaryContainer = vespera_theme_light_tertiaryContainer,
    onTertiaryContainer = vespera_theme_light_onTertiaryContainer,
    error = vespera_theme_light_error,
    errorContainer = vespera_theme_light_errorContainer,
    onError = vespera_theme_light_onError,
    onErrorContainer = vespera_theme_light_onErrorContainer,
    background = vespera_theme_light_background,
    onBackground = vespera_theme_light_onBackground,
    surface = vespera_theme_light_surface,
    onSurface = vespera_theme_light_onSurface,
    surfaceVariant = vespera_theme_light_surfaceVariant,
    onSurfaceVariant = vespera_theme_light_onSurfaceVariant,
    outline = vespera_theme_light_outline,
    inverseOnSurface = vespera_theme_light_inverseOnSurface,
    inverseSurface = vespera_theme_light_inverseSurface,
    inversePrimary = vespera_theme_light_inversePrimary,
    surfaceTint = vespera_theme_light_surfaceTint,
    outlineVariant = vespera_theme_light_outlineVariant,
    scrim = vespera_theme_light_scrim,
)

/**
 * Dark default theme color scheme.
 */
@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    primary = vespera_theme_dark_primary,
    onPrimary = vespera_theme_dark_onPrimary,
    primaryContainer = vespera_theme_dark_primaryContainer,
    onPrimaryContainer = vespera_theme_dark_onPrimaryContainer,
    secondary = vespera_theme_dark_secondary,
    onSecondary = vespera_theme_dark_onSecondary,
    secondaryContainer = vespera_theme_dark_secondaryContainer,
    onSecondaryContainer = vespera_theme_dark_onSecondaryContainer,
    tertiary = vespera_theme_dark_tertiary,
    onTertiary = vespera_theme_dark_onTertiary,
    tertiaryContainer = vespera_theme_dark_tertiaryContainer,
    onTertiaryContainer = vespera_theme_dark_onTertiaryContainer,
    error = vespera_theme_dark_error,
    errorContainer = vespera_theme_dark_errorContainer,
    onError = vespera_theme_dark_onError,
    onErrorContainer = vespera_theme_dark_onErrorContainer,
    background = vespera_theme_dark_background,
    onBackground = vespera_theme_dark_onBackground,
    surface = vespera_theme_dark_surface,
    onSurface = vespera_theme_dark_onSurface,
    surfaceVariant = vespera_theme_dark_surfaceVariant,
    onSurfaceVariant = vespera_theme_dark_onSurfaceVariant,
    outline = vespera_theme_dark_outline,
    inverseOnSurface = vespera_theme_dark_inverseOnSurface,
    inverseSurface = vespera_theme_dark_inverseSurface,
    inversePrimary = vespera_theme_dark_inversePrimary,
    surfaceTint = vespera_theme_dark_surfaceTint,
    outlineVariant = vespera_theme_dark_outlineVariant,
    scrim = vespera_theme_dark_scrim,
)

/**
 * Light android theme color scheme.
 */
@VisibleForTesting
val LightAndroidColorScheme = lightColorScheme(
    primary = android_theme_light_primary,
    onPrimary = android_theme_light_onPrimary,
    primaryContainer = android_theme_light_primaryContainer,
    onPrimaryContainer = android_theme_light_onPrimaryContainer,
    secondary = android_theme_light_secondary,
    onSecondary = android_theme_light_onSecondary,
    secondaryContainer = android_theme_light_secondaryContainer,
    onSecondaryContainer = android_theme_light_onSecondaryContainer,
    tertiary = android_theme_light_tertiary,
    onTertiary = android_theme_light_onTertiary,
    tertiaryContainer = android_theme_light_tertiaryContainer,
    onTertiaryContainer = android_theme_light_onTertiaryContainer,
    error = android_theme_light_error,
    errorContainer = android_theme_light_errorContainer,
    onError = android_theme_light_onError,
    onErrorContainer = android_theme_light_onErrorContainer,
    background = android_theme_light_background,
    onBackground = android_theme_light_onBackground,
    surface = android_theme_light_surface,
    onSurface = android_theme_light_onSurface,
    surfaceVariant = android_theme_light_surfaceVariant,
    onSurfaceVariant = android_theme_light_onSurfaceVariant,
    outline = android_theme_light_outline,
    inverseOnSurface = android_theme_light_inverseOnSurface,
    inverseSurface = android_theme_light_inverseSurface,
    inversePrimary = android_theme_light_inversePrimary,
    surfaceTint = android_theme_light_surfaceTint,
    outlineVariant = android_theme_light_outlineVariant,
    scrim = android_theme_light_scrim,
)

/**
 * Dark android theme color scheme.
 */
@VisibleForTesting
val DarkAndroidColorScheme = darkColorScheme(
    primary = android_theme_dark_primary,
    onPrimary = android_theme_dark_onPrimary,
    primaryContainer = android_theme_dark_primaryContainer,
    onPrimaryContainer = android_theme_dark_onPrimaryContainer,
    secondary = android_theme_dark_secondary,
    onSecondary = android_theme_dark_onSecondary,
    secondaryContainer = android_theme_dark_secondaryContainer,
    onSecondaryContainer = android_theme_dark_onSecondaryContainer,
    tertiary = android_theme_dark_tertiary,
    onTertiary = android_theme_dark_onTertiary,
    tertiaryContainer = android_theme_dark_tertiaryContainer,
    onTertiaryContainer = android_theme_dark_onTertiaryContainer,
    error = android_theme_dark_error,
    errorContainer = android_theme_dark_errorContainer,
    onError = android_theme_dark_onError,
    onErrorContainer = android_theme_dark_onErrorContainer,
    background = android_theme_dark_background,
    onBackground = android_theme_dark_onBackground,
    surface = android_theme_dark_surface,
    onSurface = android_theme_dark_onSurface,
    surfaceVariant = android_theme_dark_surfaceVariant,
    onSurfaceVariant = android_theme_dark_onSurfaceVariant,
    outline = android_theme_dark_outline,
    inverseOnSurface = android_theme_dark_inverseOnSurface,
    inverseSurface = android_theme_dark_inverseSurface,
    inversePrimary = android_theme_dark_inversePrimary,
    surfaceTint = android_theme_dark_surfaceTint,
    outlineVariant = android_theme_dark_outlineVariant,
    scrim = android_theme_dark_scrim,
)

/**
 * Light Android background theme
 */
val LightAndroidBackgroundTheme = BackgroundTheme(color = Color(0xFFFBFDF8))

/**
 * Dark Android background theme
 */
val DarkAndroidBackgroundTheme = BackgroundTheme(color = Color.Black)

/**
 * Vespera theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follow system by default).
 */

@Composable
fun VesperaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val dynamicThemingFlow = remember {
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { settings ->
                settings[DataStoreKeys.SETTINGS_DYNAMIC_THEMING]
                    ?: supportsDynamicTheming()
            }
    }

    val enableDynamicTheming = supportsDynamicTheming() &&
            dynamicThemingFlow.collectAsState(initial = supportsDynamicTheming()).value

    // Color scheme
    val colorScheme = when {
        enableDynamicTheming -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }

    // Background theme
    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp
    )
    val backgroundTheme = when {
        else -> defaultBackgroundTheme
    }

    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

enum class ThemeBrand {
    DEFAULT,
    ANDROID,
}