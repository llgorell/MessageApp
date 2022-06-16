package com.example.messageappebcom.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.messageappebcom.presentation.message.MessageSavedScreen
import com.example.messageappebcom.presentation.message.MessageScreen
import com.example.messageappebcom.presentation.message.destinations.MessageSavedScreenDestination
import com.example.messageappebcom.ui.MainActivity
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

import com.google.accompanist.pager.PagerState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(pagerState: PagerState,mainActivity: MainActivity) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        HorizontalPager(state = pagerState, count = 2) { page->
            when (page){
                0-> { MessageScreen(mainActivity = mainActivity)}
                1-> { MessageSavedScreen(mainActivity = mainActivity)}
            }
        }
    }

}