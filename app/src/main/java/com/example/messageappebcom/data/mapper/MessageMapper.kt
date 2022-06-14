package com.example.messageappebcom.data.mapper

import com.example.messageappebcom.data.local.MessageEntity
import com.example.messageappebcom.data.remote.dto.MessageDto
import com.example.messageappebcom.data.remote.dto.MessagesDto
import com.example.messageappebcom.domain.model.Messages

fun MessagesDto.toMessage() : Messages {
    return Messages(
        description = description,
        id = id,
        image = image,
        title = title,
        unread = unread
    )
}
fun MessageEntity.convertToMessages() : Messages{
    return Messages(
        id = id_message,
        description = description,
        image = image,
        title = title,
        unread = unread,
        saved = saved
    )
}

fun Messages.toMessageEntity():MessageEntity{
    return MessageEntity(
        description = description,
        id_message = id,
        image = image,
        title = title,
        unread = unread,
        saved = saved
    )
}
fun MessagesDto.toMessageEntity() :MessageEntity {
    return MessageEntity(
        id_message = id,
        description = description,
        image = image,
        title = title,
        unread = unread
    )
}