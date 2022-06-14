package com.example.messageappebcom.presentation.savedMessage

import com.example.messageappebcom.data.local.MessageEntity
import com.example.messageappebcom.domain.model.Messages

data class SavedMessageState (
    val data : List<Messages>? = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null,
)