package com.ewingelen.chatter.core.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.ewingelen.chatter.core.data.cloud.model.MessageCloud
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.domain.model.Message
import com.ewingelen.chatter.createChat.data.ChatToCloudMapper
import com.ewingelen.chatter.createChat.data.ChatToLocalMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
@Entity
data class ChatLocal(
    @PrimaryKey val contactId: String,
    val contactName: String,
    @ColumnInfo("contactPhoneNumber")
    val contactPhoneNumber: String,
//    @ColumnInfo("messages")
//    val messages: List<MessageLocal>
)

@Module
@InstallIn(ViewModelComponent::class)
interface ChatModule {

    @Binds
    @ViewModelScoped
    fun bindChatToLocalMapper(mapper: ChatToLocalMapper): Chat.Mapper<ChatLocal>

    @Binds
    @ViewModelScoped
    fun bindChatToCloudMapper(mapper: ChatToCloudMapper): Chat.Mapper<ChatCloud>

    @Binds
    @ViewModelScoped
    fun bindMessageToLocalMapper(mapper: MessageToLocalMapper): Message.Mapper<MessageLocal>

    @Binds
    @ViewModelScoped
    fun bindMessageToCloudMapper(mapper: MessageToCloudMapper): Message.Mapper<MessageCloud>
}

class MessageToLocalMapper @Inject constructor(): Message.Mapper<MessageLocal> {

    override fun map(text: String, mine: Boolean) = MessageLocal(text, mine)
}

class MessageToCloudMapper @Inject constructor(): Message.Mapper<MessageCloud> {

    override fun map(text: String, mine: Boolean) = MessageCloud(text, mine)
}