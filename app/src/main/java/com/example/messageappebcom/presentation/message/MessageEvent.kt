package com.example.messageappebcom.presentation.message

import com.example.messageappebcom.data.local.MessageEntity

sealed class MessageEvent () {
 data class onSaveMessage(val messageEntity: MessageEntity) :MessageEvent()
}