package com.romainbechard.contactsapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import timber.log.Timber

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

@Composable
fun createStreamColors(): Colors {
    val isInDarkMode = isSystemInDarkTheme()
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val scheme = when {
            isInDarkMode -> dynamicDarkColorScheme(LocalContext.current)
            else -> dynamicLightColorScheme(LocalContext.current)
        }
        return adapt(scheme)
    } else {
        when {
            isInDarkMode -> DarkColorPalette
            else -> LightColorPalette
        }
    }
}

@Composable
private fun adapt(scheme: ColorScheme) = Colors(
    primary = scheme.primary,
    primaryVariant = scheme.primaryContainer,
    secondary = scheme.secondary,
    secondaryVariant = scheme.secondaryContainer,
    background = scheme.background,
    surface = scheme.surface,
    error = scheme.error,
    onPrimary = scheme.onPrimary,
    onSecondary = scheme.onSecondary,
    onBackground = scheme.onBackground,
    onSurface = scheme.onSurface,
    onError = scheme.onError,
    isLight = !isSystemInDarkTheme()
)