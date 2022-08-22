package dev.fabled.splash_feature.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dev.fabled.common.components.ActionButton
import dev.fabled.common.components.PagerIndicators
import dev.fabled.common.ui.DarkPrimary
import dev.fabled.common.ui.SegoeUi
import dev.fabled.common.utils.textSlideAnimation
import dev.fabled.splash_feature.R
import dev.fabled.splash_feature.WelcomeViewModel
import dev.fabled.splash_feature.model.OnBoardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun OnBoardingScreen(welcomeViewModel: WelcomeViewModel) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val onBoardingPages = OnBoardingPage.getPages()

    var actionButtonText by remember { mutableStateOf(context.getString(R.string.next)) }
    var isLastPage by remember { mutableStateOf(false) }

    val onActionButtonClicked = remember {
        {
            if (isLastPage) welcomeViewModel.onBoardingCompleted()
            else scope.launch {
                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
            }
            Unit
        }
    }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            if (page == pagerState.pageCount - 1) {
                actionButtonText = context.getString(R.string.get_started)
                isLastPage = true
            }
        }
    }

    val skipButtonOffset by animateOffsetAsState(
        targetValue = if (!isLastPage) Offset(x = 0f, y = 0f) else Offset(x = -200f, y = 0f),
        animationSpec = tween(durationMillis = 1000)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnBoardingPager(
            pages = onBoardingPages,
            pagerState = pagerState,
            modifier = Modifier.fillMaxWidth()
        )
        OnBoardingButtons(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 30.dp)
                .fillMaxWidth(),
            actionButtonText = actionButtonText,
            skipButtonOffset = skipButtonOffset,
            onActionButtonClicked = onActionButtonClicked,
            onSkipClicked = welcomeViewModel::onBoardingCompleted
        )
    }
}

@ExperimentalPagerApi
@Composable
fun OnBoardingPager(
    pages: List<OnBoardingPage>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            contentPadding = PaddingValues(horizontal = 15.dp),
            itemSpacing = 15.dp,
            userScrollEnabled = false
        )
        { page ->
            OnBoardingPage(onBoardingPage = pages[page])
        }
        PagerIndicators(pagerState = pagerState, modifier = Modifier.padding(horizontal = 15.dp))
    }
}

@Composable
fun OnBoardingPage(onBoardingPage: OnBoardingPage) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = .5f),
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                setToSaturation(0f)
            }),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = onBoardingPage.primaryText),
            modifier = Modifier.padding(bottom = 10.dp),
            color = DarkPrimary,
            fontFamily = SegoeUi,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Text(
            text = stringResource(id = onBoardingPage.secondaryText),
            color = Color.LightGray,
            fontFamily = SegoeUi,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun OnBoardingButtons(
    modifier: Modifier = Modifier,
    actionButtonText: String,
    skipButtonOffset: Offset,
    onActionButtonClicked: () -> Unit,
    onSkipClicked: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.skip),
            modifier = Modifier
                .clickable { onSkipClicked() }
                .offset(x = skipButtonOffset.x.dp, y = skipButtonOffset.y.dp),
            color = Color.DarkGray,
            fontFamily = SegoeUi,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
        ActionButton(
            text = {
                AnimatedContent(targetState = actionButtonText, transitionSpec = {
                    textSlideAnimation().using(
                        sizeTransform = SizeTransform(clip = true)
                    )
                }) { targetText ->
                    Text(
                        text = targetText,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        color = DarkPrimary,
                        fontFamily = SegoeUi,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            },
            onClick = onActionButtonClicked
        )
    }
}