package dev.fabled.on_boarding_feature.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
import dev.fabled.on_boarding_feature.OnBoardingViewModel
import dev.fabled.on_boarding_feature.R
import dev.fabled.on_boarding_feature.model.OnBoardingPage
import dev.fabled.on_boarding_feature.utils.addAnimation
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun OnBoardingScreen(onBoardingViewModel: OnBoardingViewModel) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val onBoardingPages = OnBoardingPage.getPages()

    var actionButtonText by remember { mutableStateOf("Next") }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            if (page == pagerState.pageCount - 1) {
                actionButtonText = "Get Started!"
            }
        }
    }

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
                .padding(20.dp)
                .fillMaxWidth(),
            actionButtonText = actionButtonText,
            onActionButtonClicked = {
                if (pagerState.currentPage == pagerState.pageCount - 1) {
                    TODO("Open authorization")
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                    }
                }
            },
            onSkipClicked = {
                TODO("Open authorization")
            }
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
        HorizontalPager(count = pages.size, state = pagerState) { page ->
            OnBoardingPage(onBoardingPage = pages[page])
        }
        PagerIndicators(pagerState = pagerState, modifier = Modifier.padding(top = 15.dp))
    }
}

@Composable
fun OnBoardingPage(onBoardingPage: OnBoardingPage) {

}

@ExperimentalAnimationApi
@Composable
fun OnBoardingButtons(
    modifier: Modifier = Modifier,
    actionButtonText: String,
    onActionButtonClicked: () -> Unit,
    onSkipClicked: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.skip_button),
            modifier = Modifier.clickable { onSkipClicked() },
            color = Color.DarkGray,
            fontFamily = SegoeUi,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
        ActionButton(
            text = {
                AnimatedContent(targetState = actionButtonText, transitionSpec = {
                    addAnimation().using(
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

@Preview(showSystemUi = true)
@Composable
fun BoardingPreview() {
    OnBoardingScreen(onBoardingViewModel = OnBoardingViewModel())
}