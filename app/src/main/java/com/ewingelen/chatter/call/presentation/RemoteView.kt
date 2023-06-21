package com.ewingelen.chatter.call.presentation

import android.view.TextureView
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import io.agora.rtc.Constants
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas

@Composable
fun RemoteView(remoteListInfo: Map<Int, TextureView?>, mEngine: RtcEngine) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.2f)
    ) {
        remoteListInfo.forEach { entry ->
            val remoteTextureView = RtcEngine.CreateTextureView(context).takeIf {
                entry.value == null
            } ?: entry.value
            AndroidView(
                factory = { remoteTextureView!! },
                modifier = Modifier.size(180.dp, 240.dp)
            )
            mEngine.setupRemoteVideo(
                VideoCanvas(remoteTextureView, Constants.RENDER_MODE_HIDDEN, entry.key)
            )
        }
    }
}
