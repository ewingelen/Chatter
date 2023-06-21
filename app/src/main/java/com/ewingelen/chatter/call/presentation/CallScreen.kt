package com.ewingelen.chatter.call.presentation

import android.view.TextureView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ewingelen.chatter.R
import com.ewingelen.chatter.call.presentation.components.CallActions
import com.ewingelen.chatter.core.presentation.theme.Black900
import com.ewingelen.chatter.core.presentation.theme.White900
import io.agora.rtc.Constants
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import timber.log.Timber

@Composable
fun CallScreen(
    changeUserPresence: (Boolean) -> Unit,
    channelName: String,
    userRole: String,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    val localSurfaceView by remember {
        mutableStateOf(RtcEngine.CreateTextureView(context))
    }

    var remoteUserMap by remember {
        mutableStateOf(mapOf<Int, TextureView?>())
    }

    var videoDisabled by remember { mutableStateOf(false) }

    val mEngine = remember {
        initEngine(
            context,
            object : IRtcEngineEventHandler() {
                override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
                    Timber.d("JOIN CHANEL")
                    changeUserPresence(true)
                }

                override fun onLeaveChannel(stats: RtcStats?) {
                    super.onLeaveChannel(stats)
                    Timber.d("LEAVE CHANEL")
                    changeUserPresence(false)
                }

                override fun onUserJoined(uid: Int, elapsed: Int) {
                    val desiredUserList = remoteUserMap.toMutableMap()
                    desiredUserList[uid] = null
                    remoteUserMap = desiredUserList.toMap()
                }

                override fun onUserOffline(uid: Int, reason: Int) {
                    val desiredUserList = remoteUserMap.toMutableMap()
                    desiredUserList.remove(uid)
                    remoteUserMap = desiredUserList.toMap()
                }
            },
            channelName,
            userRole
        )
    }

//    if (userRole == "Broadcaster") {
    mEngine.setupLocalVideo(VideoCanvas(localSurfaceView, Constants.RENDER_MODE_FIT, 0))
//    }

    Box(modifier = Modifier.background(Black900)) {
        if (!videoDisabled && localSurfaceView != null) {
            AndroidView(
                factory = { localSurfaceView },
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_photo),
                contentDescription = null,
                tint = White900,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp),
            )
        }

        RemoteView(remoteListInfo = remoteUserMap, mEngine = mEngine)

        CallActions(
            videoDisabled = videoDisabled,
            onVideoToggle = { videoDisabled = !videoDisabled },
            mEngine = mEngine,
            navigateUp = navigateUp
        )
    }
}