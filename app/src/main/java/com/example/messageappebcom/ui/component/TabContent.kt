package com.example.messageappebcom.ui.component

import androidx.compose.runtime.Composable
import com.example.messageappebcom.presentation.message.MessageSavedScreen
import com.example.messageappebcom.presentation.message.MessageScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = 2) { page->
        when (page){
            0-> { MessageScreen()}
            1-> { MessageSavedScreen()}

        }
    }
}