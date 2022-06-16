package com.example.messageappebcom.presentation.message

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messageappebcom.R
import com.example.messageappebcom.data.mapper.convertToEntityRead
import com.example.messageappebcom.data.mapper.convertToEntitySaved
import com.example.messageappebcom.data.mapper.toMessageEntity
import com.example.messageappebcom.domain.model.Messages
import com.example.messageappebcom.ui.spacing
import com.example.messageappebcom.util.ComponentState
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun MessageItem(
    data: Messages,
    isChecked: Boolean,
    onClickShare: (Messages) -> Unit,
    viewModel: MessageViewModel = hiltViewModel()
) {
    var expandableState by remember { mutableStateOf(false) }
    var state = viewModel.state
    val rotationState by animateFloatAsState(
        targetValue = if (expandableState) 180f else 0f
    )
    var deleteState by remember { mutableStateOf(false) }
    var readState by remember { mutableStateOf(false) }
    var saveMessageState by remember { mutableStateOf(data.saved) }
    var readMessageState by remember { mutableStateOf(data.unread) }
    val isVisibilityImage by remember { mutableStateOf(!data.image.isNullOrEmpty()) }
    var toStateOnpressAnim by remember { mutableStateOf(ComponentState.Released) }
    val transition: Transition<ComponentState> =
        updateTransition(targetState = toStateOnpressAnim, label = "")

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

    //rtl layout
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl)
    {
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier.padding(MaterialTheme.spacing.small)
        ) {

            //if press on card
            if (isChecked) {
                Checkbox(
                    checked = deleteState,
                    onCheckedChange = { deleteState = it },
                    modifier = Modifier.wrapContentSize()
                )
            }
            Box(
                modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Card(backgroundColor = if (readMessageState) Color.White else MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(
                            horizontal = MaterialTheme.spacing.medium,
                            vertical = MaterialTheme.spacing.small
                        )
                        .combinedClickable(
                            onLongClick = {
                                val list = viewModel.state.data
                                list!!.forEach { it.visibaleCheck = !it.visibaleCheck }
                                state = state.copy(data = list)
                                viewModel.onEvent(
                                    MessageEvent.onLongClick(viewModel.state.data!!.map { it.toMessageEntity() })
                                )

                                toStateOnpressAnim = ComponentState.Pressed


                            }, onClick = {
                                toStateOnpressAnim = ComponentState.Released

                            }
                        )
                        .fillMaxWidth()
                        .graphicsLayer {
                            scaleX = scalex
                            scaleY = scaley

                        }
                        .animateContentSize(
                            animationSpec = tween(
                                delayMillis = 500,
                                durationMillis = 1000,
                                easing = LinearOutSlowInEasing

                            )
                        ),
                    shape = RoundedCornerShape(MaterialTheme.spacing.customize)
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .padding(
                                    horizontal = MaterialTheme.spacing.medium,
                                    vertical = MaterialTheme.spacing.customize
                                )
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
                                        onClickShare(data)
                                    }

                            )


                            Image(painter = if (saveMessageState) painterResource(
                                id = R.drawable.ic_baseline_bookmark_24
                            ) else
                                painterResource(
                                    id = R.drawable.ic_baseline_bookmark_border_24
                                ),

                                contentDescription = "",
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .wrapContentWidth()
                                    .weight(1f)
                                    .clickable {
                                        saveMessageState = !saveMessageState
                                        val message = data.convertToEntitySaved(saveMessageState)

                                        state.message = message
                                        state.data!!.forEach {
                                            if (it.id_message == data.id_message) {
                                                it.saved = saveMessageState
                                            }
                                        }
                                        viewModel.onEvent(MessageEvent.onSaveMessage(message))

                                    }

                            )

                        }
                        Box(
                            Modifier.padding(
                                start = MaterialTheme.spacing.medium,
                                end = MaterialTheme.spacing.medium,
                                bottom = MaterialTheme.spacing.small
                            )
                        ) {
                            Text(
                                text = data.title,
                                color = colorResource(id = R.color.black),
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        if (expandableState && isVisibilityImage) {
                            Card(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = MaterialTheme.spacing.medium,
                                        MaterialTheme.spacing.small
                                    ),
                                shape = RoundedCornerShape(MaterialTheme.spacing.extraSmall)
                            ) {
                                GlideImage(
                                    contentScale = ContentScale.Crop,
                                    // CoilImage, FrescoImage
                                    imageModel = data.image
                                )
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                        ) {
                            if (isVisibilityImage && !expandableState) {
                                Box(
                                    modifier = Modifier
                                        .width(48.dp)
                                        .height(40.dp)
                                        .padding(end = MaterialTheme.spacing.small)
                                ) {
                                    Card(shape = RoundedCornerShape(MaterialTheme.spacing.extraSmall)) {
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
                            modifier = Modifier.padding(
                                MaterialTheme.spacing.medium,
                                end = 56.dp,
                                top = MaterialTheme.spacing.customize
                            )
                        )
                        Row(
                            verticalAlignment = CenterVertically,
                            modifier = Modifier.padding(
                                vertical = MaterialTheme.spacing.customize,
                                horizontal = MaterialTheme.spacing.medium
                            )
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
                                modifier = Modifier.padding(end = MaterialTheme.spacing.medium)
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
                                        readMessageState = false
                                        val message = data.convertToEntityRead(readMessageState)

                                        state.message = message
                                        state.data!!.forEach {
                                            if (it.id_message == data.id_message) {
                                                it.unread = readMessageState
                                            }
                                        }
                                        viewModel.onEvent(MessageEvent.onReadMessage(message))
                                        readState = true
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
