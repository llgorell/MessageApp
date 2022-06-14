package com.example.messageappebcom.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
 interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(messages : List<MessageEntity>)

    @Delete
    suspend fun deleteMessage(message : MessageEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMessage(message : MessageEntity)

    @Query("SELECT * FROM  MessageEntity " +
            "ORDER BY CASE WHEN unread = 0 THEN id_message END ASC")
    suspend fun getListMessage() : List<MessageEntity>

   @Query("DELETE  FROM MessageEntity")
    suspend fun clearMessages()

   /* @Update
    suspend fun savedMessage(saved : Boolean)*/

    @Query("SELECT * FROM MessageEntity WHERE saved = 1")
      fun getSavedMessages() : LiveData<List<MessageEntity>>


}