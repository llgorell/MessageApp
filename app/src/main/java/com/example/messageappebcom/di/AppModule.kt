package com.example.messageappebcom.di

import android.app.Application
import androidx.room.Room
import com.example.messageappebcom.data.local.MessageDataBase
import com.example.messageappebcom.data.remote.MessageApi
import com.example.messageappebcom.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  ApiModule {

    @Provides
    @Singleton
    fun provideMessageApi(): MessageApi{
        return Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideMessageDatabase(app : Application) : MessageDataBase{
        return Room.databaseBuilder(
            app,
            MessageDataBase::class.java,
            "messagedb.db"
        )
        .build()
    }
}