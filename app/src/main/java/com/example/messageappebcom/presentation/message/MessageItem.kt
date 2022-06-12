package com.example.messageappebcom.presentation.message

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.messageappebcom.domain.model.Messages

@Composable
fun MessageItem(data : Messages) {
    
    Text(text = "${data.title}")
}