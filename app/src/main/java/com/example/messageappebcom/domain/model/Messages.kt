package com.example.messageappebcom.domain.model

data class Messages(
    val description: String,
    val id_message: String,
    val id: Int? = null,
    val image: String?="",
    val title: String,
    val unread: Boolean,
    val saved : Boolean = false
)
