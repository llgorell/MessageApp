package com.example.messageappebcom.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class MessageEntity (
     val description: String,
     val id_message: String,
     val image: String?="",
     val title: String,
     val unread: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
         )