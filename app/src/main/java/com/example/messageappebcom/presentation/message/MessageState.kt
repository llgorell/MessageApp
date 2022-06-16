package com.example.messageappebcom.presentation.message

import com.example.messageappebcom.data.local.MessageEntity
import com.example.messageappebcom.domain.model.Messages

data class MessageState (
    var data : List<Messages>? = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null,
    var message : MessageEntity? = null

        )