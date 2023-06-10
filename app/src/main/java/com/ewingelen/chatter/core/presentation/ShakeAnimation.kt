package com.ewingelen.chatter.core.presentation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

fun Modifier.shake(
    enabled: Boolean,
    initialValue: Float = -2f,
    targetValue: Float = 2f
) = composed(
    factory = {
        val infiniteTransition = rememberInfiniteTransition()
        val position by infiniteTransition.animateFloat(
            initialValue = initialValue,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(tween(100), RepeatMode.Reverse)
        )
        Modifier.offset(x = if (enabled) position.dp else 0.dp, y = 0.dp)
    }
)