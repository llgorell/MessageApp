package com.example.messageappebcom.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import com.example.messageappebcom.presentation.message.MessageScreen
import com.example.messageappebcom.presentation.message.NavGraphs
import com.example.messageappebcom.ui.component.TopappBar
import com.example.messageappebcom.ui.theme.MessageAppEbcomTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageAppEbcomTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {
                    Scaffold(topBar = { TopappBar() }){
                    }
                    DestinationsNavHost(navGraph= NavGraphs.root)
                }
            }
        }
    }
}
