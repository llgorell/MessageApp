package com.example.messageappebcom.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.messageappebcom.ui.MainActivity
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen(mainActivity: MainActivity) {

    val pagerState = rememberPagerState(2)
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(modifier = Modifier.background(MaterialTheme.colors.primary)) {
            Tabs(pagerState = pagerState)
            TabContent(pagerState = pagerState, mainActivity)
        }
    }
}
