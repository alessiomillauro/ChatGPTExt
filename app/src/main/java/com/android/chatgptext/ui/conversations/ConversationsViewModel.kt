package com.android.chatgptext.ui.conversations

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.android.chatgptext.models.ConversationModel
import com.android.chatgptext.models.MessageModel
import com.android.chatgptext.repository.ConversationRepository
import com.android.chatgptext.repository.MessageRepository
import com.android.chatgptext.repository.OpenAIRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ConversationsViewModel @Inject constructor(
    private val conversationRepo: ConversationRepository,
    private val messageRepo: MessageRepository,
    private val openAIRepo: OpenAIRepositoryImpl
) : ViewModel() {

    private val _currentConversation: MutableStateFlow<String> =
        MutableStateFlow(Date().time.toString())
    private val _conversations: MutableStateFlow<MutableList<ConversationModel>> = MutableStateFlow(
        mutableListOf()
    )

    private val _messages: MutableStateFlow<HashMap<String, MutableList<MessageModel>>> =
        MutableStateFlow(HashMap())
    private val _isFetching: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isFabExpanded = MutableStateFlow(false)

    val currentConversationState: StateFlow<String> = _currentConversation.asStateFlow()
    val conversationState: StateFlow<MutableList<ConversationModel>> = _conversations.asStateFlow()
    val messageState: StateFlow<HashMap<String, MutableList<MessageModel>>> =
        _messages.asStateFlow()

    val isFetching = _isFetching.asStateFlow()
    val isFabExpanded: StateFlow<Boolean> get() = _isFabExpanded

    private var stopReceivingResult = false

    suspend fun initialize() {
        _isFetching.value = true

        _conversations.value = conversationRepo.fetchConversations()

        if (_conversations.value.isNotEmpty()) {
            _currentConversation.value = _conversations.value.first().id
            fetchMessage()
        }

        _isFetching.value = false
    }

    private suspend fun fetchMessage() {
        if (_currentConversation.value.isEmpty() || _messages.value[_currentConversation.value] != null) return

        val flow: Flow<List<MessageModel>> = messageRepo.fetchMessages(_currentConversation.value)

        flow.collectLatest {
            setMessages(it.toMutableList())
        }
    }

    private fun setMessages(messages: MutableList<MessageModel>) {
        val messagesMap: HashMap<String, MutableList<MessageModel>> =
            _messages.value.clone() as HashMap<String, MutableList<MessageModel>>

        messagesMap[_currentConversation.value] = messages

        _messages.value = messagesMap
    }

    fun stopReceveingResults() {
        stopReceivingResult = true
    }

    private fun setFabExpanded(expanded: Boolean) {
        _isFabExpanded.value = expanded
    }
}