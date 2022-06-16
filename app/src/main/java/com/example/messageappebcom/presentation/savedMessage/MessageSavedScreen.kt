package com.example.messageappebcom.presentation.message

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messageappebcom.data.mapper.convertToMessages
import com.example.messageappebcom.domain.model.Messages
import com.example.messageappebcom.ui.MainActivity
import com.example.messageappebcom.ui.spacing
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination()
fun MessageSavedScreen(mainActivity : MainActivity, viewmodel : MessageViewModel = hiltViewModel()) {
    var list by remember {
        mutableStateOf<List<Messages>>(listOf())
    }

    val messageViewModel =
        ViewModelProvider(mainActivity)[MessageViewModel::class.java]
    messageViewModel.livedata.observe(mainActivity){
        list = it.filter { it.saved }
    }

    val context = LocalContext.current
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)){
        items(list.size){
            MessageItem(data = list[it], onClickShare = { message->
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND_MULTIPLE
                    putExtra(Intent.EXTRA_STREAM, message.image)
                    putExtra(Intent.EXTRA_SUBJECT, message.title)
                    putExtra(Intent.EXTRA_TEXT, message.description)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)

            }, isChecked = list[it].visibaleCheck)
        }
    }
}