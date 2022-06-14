package com.example.messageappebcom.presentation.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messageappebcom.data.mapper.convertToMessages
import com.example.messageappebcom.ui.MainActivity
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun MessageSavedScreen(mainActivity : MainActivity, viewmodel : MessageViewModel = hiltViewModel()) {
    val list = viewmodel.savedList
    val messageViewModel =
        ViewModelProvider(mainActivity)[MessageViewModel::class.java]
    messageViewModel.getSavedMessage().observe(mainActivity) { listSavedMessages->
        val data = listSavedMessages.map { it.convertToMessages() }
        messageViewModel.savedList.value = data
    }
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)){

        items(list.value.size){
            MessageItem(data = list.value[it])
        }
    }
}