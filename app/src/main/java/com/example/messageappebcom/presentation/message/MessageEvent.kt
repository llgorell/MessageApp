package com.example.messageappebcom.presentation.message

import com.example.messageappebcom.data.local.MessageEntity

sealed class MessageEvent () {
 data class onSaveMessage(val messageEntity: MessageEntity) :MessageEvent()
 data class onLongClick(val listMessages : List<MessageEntity>) :MessageEvent()
 data class onReadMessage(val messageEntity: MessageEntity) :MessageEvent()
}