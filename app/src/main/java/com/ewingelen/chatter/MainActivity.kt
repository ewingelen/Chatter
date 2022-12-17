package com.ewingelen.chatter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ewingelen.chatter.core.ui.ScreenTheme
import com.ewingelen.chatter.core.ui.theme.ChatterTheme
import com.ewingelen.chatter.onboarding.ui.OnBoardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatterTheme {
                ScreenTheme {
                    OnBoardingScreen(
                        launchSignUpScreen = {
                        }
                    )
                }
            }
        }
    }
}
