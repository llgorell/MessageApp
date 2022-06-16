package com.example.messageappebcom.presentation.message

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messageappebcom.data.local.MessageEntity
import com.example.messageappebcom.data.mapper.convertToEntitySaved
import com.example.messageappebcom.data.mapper.convertToMessages
import com.example.messageappebcom.data.mapper.toMessageEntity
import com.example.messageappebcom.domain.model.Messages
import com.example.messageappebcom.domain.repository.MessageRepository
import com.example.messageappebcom.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val repository: MessageRepository
) : ViewModel() {
    var state by mutableStateOf(MessageState())

    private var data: MutableLiveData<List<Messages>> = MutableLiveData()
    var livedata: LiveData<List<Messages>> = data

    init {
        getMessages()
        getdata()

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
            repository.updateMessage(messageEntity)
        }
    }

    private fun updateMessage(list: List<MessageEntity> = state.data!!.map { it.toMessageEntity() }) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateListMessage(list)
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
            is MessageEvent.onLongClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    state = state.copy(data = event.listMessages.map { it.convertToMessages() })
                    delay(300)
                    updateMessage()

                }
            }
            is MessageEvent.onReadMessage -> {
                viewModelScope.launch(Dispatchers.IO) {
                    state = state.copy(message = event.messageEntity)
                    delay(300)
                    updateMessage()
                }

            }
        }
    }

    fun getSavedMessage(): LiveData<List<MessageEntity>> {
        return repository.getSavedMessage()
    }

    fun getdata() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getdata().collect { it ->

                data.postValue(it.map { it.convertToMessages() })
            }
        }
    }
}