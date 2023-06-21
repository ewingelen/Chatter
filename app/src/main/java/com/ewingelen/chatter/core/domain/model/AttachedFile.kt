package com.ewingelen.chatter.core.domain.model

import androidx.annotation.Keep
import com.google.firebase.firestore.PropertyName

@Keep
data class AttachedFile(
    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String = "",
    @get:PropertyName("downloadUrl")
    @set:PropertyName("downloadUrl")
    var downloadUrl: String = "",
    @get:PropertyName("type")
    @set:PropertyName("type")
    var type: String = "",
    @get:PropertyName("size")
    @set:PropertyName("size")
    var size: String = ""
)