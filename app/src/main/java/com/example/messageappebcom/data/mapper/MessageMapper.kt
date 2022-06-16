package com.example.messageappebcom.data.mapper

import com.example.messageappebcom.data.local.MessageEntity
import com.example.messageappebcom.data.remote.dto.MessageDto
import com.example.messageappebcom.data.remote.dto.MessagesDto
import com.example.messageappebcom.domain.model.Messages

fun MessagesDto.toMessage() : Messages {
    return Messages(
        description = description,
        id_message = id,
        image = image,
        title = title,
        unread = unread,
    )
}
fun MessageEntity.convertToMessages() : Messages{
    return Messages(
        id_message = id_message,
        description = description,
        image = image,
        title = title,
        unread = unread,
        saved = saved,
        id = id,
        is_checked = is_checked,
        visibaleCheck = visibale_check
    )
}

fun Messages.toMessageEntity():MessageEntity{
    return MessageEntity(
        description = description,
        id_message = id_message,
        image = image,
        title = title,
        unread = unread,
        saved = saved,
        id = id,
        is_checked = is_checked,
        visibale_check = visibaleCheck
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
fun Messages.convertToEntitySaved(saved : Boolean) : MessageEntity{
    return MessageEntity(
        description = description,
        id_message = id_message,
        id = id,
        title = title,
        image = image,
        unread =  unread,
        is_checked = is_checked,
        visibale_check = visibaleCheck,
        saved = saved
    )
}
fun Messages.convertToEntityRead(read : Boolean) : MessageEntity{
    return MessageEntity(
        description = description,
        id_message = id_message,
        id = id,
        title = title,
        image = image,
        unread =  read,
        is_checked = is_checked,
        visibale_check = visibaleCheck,
        saved = saved
    )
}