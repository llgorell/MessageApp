package com.example.messageappebcom.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class MessageEntity (
    val description: String,
    val id_message: String,
    val image: String?="",
    val title: String,
    val unread: Boolean,
    @ColumnInfo(name = "saved")  val saved : Boolean = false,
    val is_checked: Boolean = false,
    val visibale_check: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
         )