package com.android.chatgptext.di

import com.android.chatgptext.repository.ConversationRepository
import com.android.chatgptext.repository.ConversationRepositoryImpl
import com.android.chatgptext.repository.MessageRepository
import com.android.chatgptext.repository.MessageRepositoryImpl
import com.android.chatgptext.repository.OpenAIRepository
import com.android.chatgptext.repository.OpenAIRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun conversationRepository(repo: ConversationRepositoryImpl): ConversationRepository

    @Binds
    abstract fun messageRepository(repo: MessageRepositoryImpl): MessageRepository

    @Binds
    abstract fun openAIRepository(repo: OpenAIRepositoryImpl): OpenAIRepository
}