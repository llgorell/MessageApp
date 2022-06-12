package com.example.messageappebcom.presentation.message

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun MessageScreen(
    navigator: DestinationsNavigator,
    viewmodel : MessageViewModel = hiltViewModel()) {
    val state = viewmodel.state


    LazyColumn(){
        items(state.data!!.size){ index ->
            MessageItem(state.data[index])
        }
    }

}