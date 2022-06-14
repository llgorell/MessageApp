package com.example.messageappebcom.ui.component

import android.provider.Settings.Global.getString
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.content.res.TypedArrayUtils.getString
import com.example.messageappebcom.R
import com.example.messageappebcom.util.Resource

@Composable
fun TopAppBar() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Column {
                Row(Modifier.padding(top = 20.dp)) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp)
                    ) {
                        Icon(
                            modifier = Modifier.scale(scaleX = -1f, scaleY = -1f),
                            imageVector = Icons.Filled.ArrowBack, contentDescription = ""
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.my_messages),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .padding(start = 32.dp)
                            .width(92.dp)
                            .wrapContentHeight()

                    )
                }
                TabScreen()
            }
        }
    }
}