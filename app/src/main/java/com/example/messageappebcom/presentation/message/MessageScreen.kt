package com.example.messageappebcom.presentation.message

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.messageappebcom.R
import com.example.messageappebcom.domain.model.Messages
import com.example.messageappebcom.ui.MainActivity
import com.example.messageappebcom.ui.spacing
import com.example.messageappebcom.util.ComponentState
import com.ramcosta.composedestinations.annotation.Destination


@Composable
@Destination(start = true)
fun MessageScreen(
    mainActivity: MainActivity,
    viewmodel: MessageViewModel = hiltViewModel()
) {

    var list by remember {
        mutableStateOf(viewmodel.state.data)
    }
    var state by remember {
        mutableStateOf(viewmodel.state)
    }

    val visibaleCheckBox by remember { mutableStateOf(state.data!![0].visibaleCheck) }


    val messageViewModel =
        ViewModelProvider(mainActivity)[MessageViewModel::class.java]
    messageViewModel.livedata.observe(mainActivity) {
        state = state.copy(data = it)

    }


    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(list!!.size) { index ->

                MessageItem(list!![index], onClickShare = { message ->
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND_MULTIPLE
                        putExtra(Intent.EXTRA_STREAM, message.image)
                        putExtra(Intent.EXTRA_SUBJECT, message.title)
                        putExtra(Intent.EXTRA_TEXT, message.description)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)

                }, isChecked = visibaleCheckBox)
            }
        }

        if (visibaleCheckBox) {
            Box() {
                Row(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                    Button(
                        shape = RoundedCornerShape(8.dp), colors =
                        ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.orange)),
                        onClick = { ComponentState.Released },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                            .height(40.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            color = White,
                            style = MaterialTheme.typography.body1
                        )
                    }

                    TextButton(
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(
                            width = 1.dp,
                            color = colorResource(id = R.color.orange)
                        ),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Transparent),
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                            .height(40.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.delete),
                            color = colorResource(id = R.color.orange),
                            style = MaterialTheme.typography.body1
                        )

                    }
                }
            }
        }
    }
}