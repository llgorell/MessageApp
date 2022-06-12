package com.example.messageappebcom.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MessageEntity::class],
    version = 1
)
 abstract class MessageDataBase : RoomDatabase(){
     abstract val  dao : MessageDao
}