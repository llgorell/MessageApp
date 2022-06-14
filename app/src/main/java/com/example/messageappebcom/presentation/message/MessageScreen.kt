package com.example.messageappebcom.presentation.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun MessageScreen(
    viewmodel : MessageViewModel = hiltViewModel()) {
    val state = viewmodel.state

    Column(modifier = Modifier.fillMaxSize().background(color= MaterialTheme.colors.background)) {
        LazyColumn(){
            items(state.data!!.size){ index ->
                MessageItem(state.data[index])
            }
        }
    }



}