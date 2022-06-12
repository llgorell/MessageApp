package com.example.messageappebcom.domain.repository

import com.example.messageappebcom.domain.model.Messages
import com.example.messageappebcom.util.Resource
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    suspend fun getMessages() : Flow<Resource<List<Messages>>>
}