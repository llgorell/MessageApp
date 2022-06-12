package com.example.messageappebcom.presentation.message

import com.example.messageappebcom.domain.model.Messages

data class MessageState (
    val data : List<Messages>? = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null
        )