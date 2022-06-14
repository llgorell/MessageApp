package com.example.messageappebcom.presentation.message

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.messageappebcom.R
import com.example.messageappebcom.domain.model.Messages
import com.example.messageappebcom.util.ComponentState
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun MessageItem(data: Messages) {
    var expandableState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandableState) 180f else 0f
    )
    var deleteState by remember { mutableStateOf(false) }
    var visibaleCheckbox by remember { mutableStateOf(false) }
    var isVisibility by remember { mutableStateOf(!data.image.isNullOrEmpty()) }
    var toState by remember { mutableStateOf(ComponentState.Released) }
    val transition: Transition<ComponentState> = updateTransition(targetState = toState, label = "")

// Defines a float animation to scale x,y
    val scalex: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 50f) }, label = ""
    ) { state ->
        if (state == ComponentState.Pressed) 0.90f else 1f
    }
    val scaley: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 50f) }, label = ""
    ) { state ->
        if (state == ComponentState.Pressed) 0.90f else 1f
    }
    val modifier = Modifier.pointerInput(Unit) {
        detectTapGestures(

        )
    }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl)
    {
        Row(verticalAlignment = CenterVertically) {

            //if press on card
            if (visibaleCheckbox) {
                Checkbox(checked = deleteState, onCheckedChange = { deleteState = it })
            }
            Box(
                modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Card(backgroundColor = if (data.unread) Color.White else MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .wrapContentHeight()
                        .combinedClickable(
                            onLongClick = {
                                visibaleCheckbox = !visibaleCheckbox
                                          toState = ComponentState.Pressed

                            }, onClick = {
                                toState = ComponentState.Released
                                visibaleCheckbox = !visibaleCheckbox
                            }
                        )
                        .fillMaxWidth()
                        .graphicsLayer {
                            scaleX = scalex
                            scaleY = scaley

                        }
                        .animateContentSize(
                            animationSpec = tween(
                                delayMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 13.dp)
                                .fillMaxWidth(),
                            verticalAlignment = CenterVertically
                        ) {

                            Text(
                                text = stringResource(id = R.string.time_message),
                                color = colorResource(id = R.color.font_gray),
                                style = MaterialTheme.typography.subtitle2,
                                modifier = Modifier
                                    .weight(10f)
                                    .wrapContentHeight()
                                    .align(alignment = Alignment.Top)


                            )
                            Image(imageVector = Icons.Outlined.Share,
                                contentDescription = "",
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .wrapContentWidth()
                                    .weight(1f)
                                    .clickable {
                                        //TODO click on share
                                    }

                            )


                            Image(imageVector = Icons.Filled.BookmarkBorder,
                                contentDescription = "",
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .wrapContentWidth()
                                    .weight(1f)
                                    .clickable {
                                        //TODO click on save
                                    }

                            )

                        }
                        Box(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)) {
                            Text(
                                text = data.title,
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        if (expandableState && isVisibility) {
                            Card(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                GlideImage(
                                    contentScale = ContentScale.Crop,
                                    // CoilImage, FrescoImage
                                    imageModel = data.image
                                    // shows a shimmering effect when loading an image.
                                    /*  shimmerParams = ShimmerParams(
                                          baseColor = MaterialTheme.colors.background,
                                          highlightColor = Color.Gray,
                                          durationMillis = 700,
                                          dropOff = 0.65f,
                                          tilt = 20f
                                      ),*/
                                )
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            if (isVisibility && !expandableState) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(end = 8.dp)
                                ) {
                                    Card(shape = RoundedCornerShape(4.dp)) {
                                        GlideImage(
                                            contentScale = ContentScale.Crop,
                                            // CoilImage, FrescoImage
                                            imageModel = data.image,
                                            // shows a shimmering effect when loading an image.
                                            shimmerParams = ShimmerParams(
                                                baseColor = MaterialTheme.colors.background,
                                                highlightColor = Color.Gray,
                                                durationMillis = 700,
                                                dropOff = 0.65f,
                                                tilt = 20f
                                            ),
                                        )
                                    }
                                }
                            }
                            Text(
                                text = data.description,
                                style = MaterialTheme.typography.body2,
                                color = colorResource(id = R.color.black_desc),
                                maxLines = if (!expandableState) 1 else 9,
                                overflow = if (!expandableState) TextOverflow.Ellipsis else TextOverflow.Visible

                            )
                        }
                        Divider(
                            thickness = 1.dp, color = colorResource(R.color.divider),
                            modifier = Modifier.padding(start = 16.dp, end = 56.dp, top = 12.dp)
                        )
                        Row(
                            verticalAlignment = CenterVertically,
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.expire_message),
                                color = colorResource(id = R.color.font_gray),
                                style = MaterialTheme.typography.subtitle2,
                                modifier = Modifier.weight(6f)
                            )
                            Text(
                                text = stringResource(id = R.string.time_expires_message),
                                color = colorResource(id = R.color.font_gray),
                                style = MaterialTheme.typography.subtitle2,
                                modifier = Modifier.padding(end = 16.dp)
                            )

                            Icon(
                                imageVector = Icons.Outlined.ExpandMore,
                                contentDescription = "drop down arrow",
                                tint = MaterialTheme.colors.primaryVariant,
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colors.primaryVariant,
                                        shape = RoundedCornerShape(50.dp)
                                    )
                                    .size(24.dp)
                                    .alpha(ContentAlpha.medium)
                                    .rotate(rotationState)
                                    .clickable {
                                        expandableState = !expandableState
                                    }
                            )

                        }
                    }
                }
            }
        }
    }
}
