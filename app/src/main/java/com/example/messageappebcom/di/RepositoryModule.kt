package com.example.messageappebcom.di

import com.example.messageappebcom.data.repository.MessageRepositoryImpl
import com.example.messageappebcom.domain.repository.MessageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: MessageRepositoryImpl): MessageRepository
}