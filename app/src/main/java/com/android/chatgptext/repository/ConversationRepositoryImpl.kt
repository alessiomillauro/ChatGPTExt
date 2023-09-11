package com.android.chatgptext.repository

import com.android.chatgptext.models.ConversationModel
import com.android.chatgptext.utils.conversationCollection
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ConversationRepositoryImpl @Inject constructor(
    private val fsInstance: FirebaseFirestore
) : ConversationRepository {
    override suspend fun fetchConversations(): MutableList<ConversationModel> {
        if (getFirebaseSnapshot().documents.isNotEmpty()) {
            val documents = getFirebaseSnapshot().documents

            return documents.map {
                it.toObject(ConversationModel::class.java)
            }.toList() as MutableList<ConversationModel>
        }

        return mutableListOf()
    }

    override fun newConversation(conversation: ConversationModel): ConversationModel {
        fsInstance.collection(conversationCollection).add(conversation)
        return conversation
    }

    override suspend fun deleteConversation(conversationId: String) {

    }

    private suspend fun getFirebaseSnapshot() = fsInstance.collection(conversationCollection)
        .orderBy("createdBy", Query.Direction.DESCENDING).get().await()
}