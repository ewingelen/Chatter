package com.ewingelen.chatter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.ewingelen.chatter.core.presentation.theme.ChatterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatterTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(),
                    color = MaterialTheme.colors.background
                ) {
                }
            }
        }
    }
}
