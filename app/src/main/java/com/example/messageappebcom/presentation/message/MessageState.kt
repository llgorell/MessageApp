package com.example.messageappebcom.presentation.message

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.messageappebcom.data.local.MessageEntity
import com.example.messageappebcom.domain.model.Messages

data class MessageState (
    val data : List<Messages>? = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null,
    var message : MessageEntity? = null

        )