package com.example.messageappebcom.ui

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.messageappebcom.presentation.message.NavGraphs
import com.example.messageappebcom.ui.component.TopAppBar
import com.example.messageappebcom.ui.theme.MessageAppEbcomTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
 class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageAppEbcomTheme {
                // A surface container usisurng the 'background' color from the theme
                Scaffold() {
                    TopAppBar(this)
                }

            }
        }
    }
}
