package com.example.messageappebcom.domain.model

data class Messages(
    val description: String,
    val id_message: String,
    val id: Int? = null,
    val image: String? = "",
    val title: String,
    var unread: Boolean,
    var saved: Boolean = false,
    var is_checked: Boolean = false,
    var visibaleCheck: Boolean = false,
)
