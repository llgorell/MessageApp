package com.example.messageappebcom.domain.model

data class Messages(
    val description: String,
    val id: String,
    val image: String?="",
    val title: String,
    val unread: Boolean
)
