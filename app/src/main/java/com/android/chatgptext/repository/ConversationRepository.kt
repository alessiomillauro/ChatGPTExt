package com.android.chatgptext.repository

import com.android.chatgptext.models.ConversationModel

interface ConversationRepository {
    suspend fun fetchConversations(): MutableList<ConversationModel>
    fun newConversation(conversation: ConversationModel): ConversationModel
    suspend fun deleteConversation(conversationId: String)
}