package com.example.messageappebcom.domain.repository

import androidx.lifecycle.LiveData
import com.example.messageappebcom.data.local.MessageEntity
import com.example.messageappebcom.domain.model.Messages
import com.example.messageappebcom.util.Resource
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    suspend fun getMessages() : Flow<Resource<List<Messages>>>
    suspend fun updateMessage(message :MessageEntity)
    suspend fun updateListMessage(list :List<MessageEntity>)
     fun getSavedMessage() : LiveData<List<MessageEntity>>
     fun getdata() : Flow<List<MessageEntity>>
}