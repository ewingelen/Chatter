package com.ewingelen.chatter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ewingelen.chatter.core.presentation.navigation.AppNavHost
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ChatterTheme {
                AppNavHost()
            }
        }
    }
}
