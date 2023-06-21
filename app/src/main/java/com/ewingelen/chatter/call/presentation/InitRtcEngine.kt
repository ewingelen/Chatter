package com.ewingelen.chatter.call.presentation

import android.content.Context
import com.ewingelen.chatter.BuildConfig
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine

fun initEngine(
    current: Context,
    eventHandler: IRtcEngineEventHandler,
    channelName: String,
    userRole: String
): RtcEngine = RtcEngine.create(current, BuildConfig.AGORA_ID, eventHandler).apply {
    enableVideo()
    setChannelProfile(1)
//    if (userRole == "Broadcaster") {
        setClientRole(1)
//    } else {
//        setClientRole(0)
//    }
    //TODO: token
    joinChannel("", channelName, "", 0)
}