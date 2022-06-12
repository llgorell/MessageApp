package com.example.messageappebcom.presentation.message

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messageappebcom.domain.repository.MessageRepository
import com.example.messageappebcom.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val repository: MessageRepository
) : ViewModel() {

     var state by mutableStateOf(MessageState())


    init {
        getMessages()
    }

     private fun getMessages(){
        viewModelScope.launch {
            repository
                .getMessages()
                .collect{ result ->
                    when (result) {

                        is Resource.Success -> {
                            result.data.let{
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
}