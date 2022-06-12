package com.example.messageappebcom.data.remote

import com.example.messageappebcom.data.remote.dto.MessageDto
import retrofit2.http.GET

interface MessageApi {

    @GET("/v3/729e846c-80db-4c52-8765-9a762078bc82")
    suspend fun getMessages(): MessageDto
}