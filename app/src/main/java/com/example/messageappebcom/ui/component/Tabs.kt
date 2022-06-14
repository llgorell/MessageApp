package com.example.messageappebcom.ui.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.messageappebcom.ui.theme.font_gray
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState : PagerState) {
    val list = listOf("عمومی","ذخیره شده")
    val scope = rememberCoroutineScope()
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {


        TabRow(selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.Black ,   // if selected black else gray

            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState,tabPositions),
                    height = 4.dp,
                    color = MaterialTheme.colors.primaryVariant

                )
            }

        ) {

            list.forEachIndexed { index, s ->
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl)
                {
                    Tab(
                        text = {
                            Text(text =
                            list[index],
                                color = if (pagerState.currentPage == index) Color.Black else font_gray,
                                style = MaterialTheme.typography.button,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }


            }
        }
    }
}