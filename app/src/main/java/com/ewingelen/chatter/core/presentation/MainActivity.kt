package com.ewingelen.chatter.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ewingelen.chatter.core.presentation.navigation.AppNavHost
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import com.ewingelen.chatter.core.presentation.theme.ScreenTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ChatterTheme {
                ScreenTheme {
                    AppNavHost()
                }
            }
        }
    }
}
