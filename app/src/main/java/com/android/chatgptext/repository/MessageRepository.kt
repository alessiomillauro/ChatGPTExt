package com.android.chatgptext.repository

import com.android.chatgptext.models.MessageModel
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    fun fetchMessages(conversationId: String): Flow<List<MessageModel>>
    fun createMessage(message: MessageModel): MessageModel
    fun deleteMessage()
}