package com.ewingelen.chatter.core.data.cloud.model

import androidx.annotation.Keep
import com.ewingelen.chatter.core.domain.model.AttachedFile
import com.google.firebase.firestore.PropertyName

@Keep
data class MessageCloud(
    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = "",
    @get:PropertyName("senderId")
    @set:PropertyName("senderId")
    var senderId: String = "",
    @get:PropertyName("text")
    @set:PropertyName("text")
    var text: String = "",
    @get:PropertyName("file")
    @set:PropertyName("file")
    var file: AttachedFile? = null,
) {
    interface Mapper<T> {

        fun map(id: String, text: String, senderId: String, file: AttachedFile?): T
    }

    fun <T> map(mapper: Mapper<T>) =
        mapper.map(id = id, text = text, senderId = senderId, file = file)
}
