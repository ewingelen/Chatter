package com.ewingelen.chatter.main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ewingelen.chatter.core.presentation.navigation.AppNavHost
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithBackground
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<MainViewModel>()
        splashScreen.apply {
            setKeepOnScreenCondition {
                viewModel.state.value.loading
            }
            setOnExitAnimationListener { splash ->
                splash.remove()
                setContent {
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    ChatterThemeWithBackground(modifier = Modifier.fillMaxSize()) {
                        AppNavHost(
                            verifyPhoneNumber = { verify ->
                                verify.verify(this@MainActivity)
                            },
                            isUserAuthorized = state.userAuthorized
                        )
                    }
                }
            }
        }
    }
}