package com.android.chatgptext.models

import java.util.Date

data class MessageModel(
    var id: String = Date().time.toString(),
    var conversationId: String = "",
    val question: String = "",
    var answer: String = "",
    var createdAt: Date = Date()
)
