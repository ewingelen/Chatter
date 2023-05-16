package com.ewingelen.chatter.main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ewingelen.chatter.core.presentation.components.ChatterScaffold
import com.ewingelen.chatter.core.presentation.navigation.AppNavHost
import com.ewingelen.chatter.core.presentation.theme.ChatterThemeWithSurface
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                lifecycleScope.launch {
                    //TODO: fix and remove delay
                    delay(500L)
                    splash.remove()
                }
            }
        }
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()
            val coroutineScope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }
            ChatterThemeWithSurface(modifier = Modifier.fillMaxSize()) {
                ChatterScaffold(snackbarHostState = snackbarHostState) {
                    AppNavHost(
                        verifyPhoneNumber = { verify ->
                            verify.verify(this@MainActivity)
                        },
                        isUserAuthorized = state.userAuthorized,
                        showSnackbar = { message ->
                            coroutineScope.launch {
                                val job = coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = message,
                                        duration = SnackbarDuration.Indefinite
                                    )
                                }
                                delay(1000L)
                                job.cancel()
                            }
                        }
                    )
                }
            }
        }
    }
}