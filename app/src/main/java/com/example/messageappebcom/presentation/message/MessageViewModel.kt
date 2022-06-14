package com.example.messageappebcom.presentation.message

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messageappebcom.data.local.MessageEntity
import com.example.messageappebcom.data.mapper.convertToMessages
import com.example.messageappebcom.domain.model.Messages
import com.example.messageappebcom.domain.repository.MessageRepository
import com.example.messageappebcom.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val repository: MessageRepository
) : ViewModel() {
    var state by mutableStateOf(MessageState())
     val savedList = mutableStateOf<List<Messages>>(listOf())

    init {
        getMessages()
    }

    private fun getMessages() {
        viewModelScope.launch {
            repository
                .getMessages()
                .collect { result ->
                    when (result) {

                        is Resource.Success -> {
                            result.data.let {
                                state = state.copy(data = result.data)
                            }
                        }
                        is Resource.Error -> {
                            state = state.copy(error = result.message)
                        }

                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }

    }

    private fun savedMessage(messageEntity: MessageEntity = state.message!!) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveMessage(messageEntity)
        }
    }

    fun onEvent(event: MessageEvent) {
        when (event) {
            is MessageEvent.onSaveMessage -> {
                state = state.copy(message = event.messageEntity)
                viewModelScope.launch(Dispatchers.IO) {
                    delay(300)
                    savedMessage()
                }

            }
        }
    }
    fun getSavedMessage() : LiveData<List<MessageEntity>>{
           return  repository.getSavedMessage()
    }
}