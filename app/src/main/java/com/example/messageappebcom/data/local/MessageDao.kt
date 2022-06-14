package com.example.messageappebcom.data.local

import androidx.room.*
import com.example.messageappebcom.data.remote.dto.MessageDto

@Dao
 interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(messages : List<MessageEntity>)

    @Delete
    suspend fun deleteMessage(message : MessageEntity)

    @Update
    suspend fun updateMessage(message : MessageEntity)

    @Query("SELECT * FROM  MessageEntity " +
            "ORDER BY CASE WHEN unread = 0 THEN id_message END ASC")
    suspend fun getListMessage() : List<MessageEntity>

   @Query("DELETE  FROM MessageEntity")
    suspend fun clearMessages()

   /* @Update
    suspend fun savedMessage(saved : Boolean)*/

    @Query("SELECT * FROM MessageEntity WHERE saved = 1 ")
    suspend fun getSavedMessages() : List<MessageEntity>


}