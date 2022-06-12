package com.example.messageappebcom.data.remote.dto

data class MessagesDto(
    val title: String,
    val description: String,
    val image: String? = "",
    val id: String,
    val unread: Boolean
)