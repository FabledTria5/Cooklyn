package dev.fabled.recommendations_feature.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dev.fabled.common.components.ActionButton
import dev.fabled.common.components.PagesProgressIndicator
import dev.fabled.common.ui.DarkPrimary
import dev.fabled.common.ui.SegoeUi
import dev.fabled.common.utils.textFadeAnimation
import dev.fabled.common.utils.textSlideAnimation
import dev.fabled.domain.model.FilterItem
import dev.fabled.domain.model.Resource
import dev.fabled.recommendations_feature.R
import dev.fabled.recommendations_feature.RecommendationsViewModel
import dev.fabled.recommendations_feature.pages.RecommendationsPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun RecommendationsScreen(recommendationsViewModel: RecommendationsViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val pagesNames = stringArrayResource(id = R.array.pages_names)

    val screenState = recommendationsViewModel.recommendationsScreenState

    val cuisines = recommendationsViewModel.cuisinesList
    val diets = recommendationsViewModel.dietsList
    val intolerances = recommendationsViewModel.intolerancesList

    val onButtonClick = remember {
        {
            if (pagerState.currentPage == pagerState.pageCount - 1)
                recommendationsViewModel.onFinishedRecommendations()
            else {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
                Unit
            }
        }
    }

    var pageText by remember { mutableStateOf(pagesNames.first()) }
    var actionButtonText by remember { mutableStateOf(context.getString(R.string.next)) }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            pageText = pagesNames[page]

            if (page == pagerState.pageCount - 1)
                actionButtonText = context.getString(R.string.lets_go)
        }
    }

    if (screenState is Resource.Loading)
        RecommendationsLoading(modifier = Modifier.fillMaxSize())

    RecommendationsScreenContent(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxSize(),
        enabled = screenState !is Resource.Loading,
        pagerState = pagerState,
        pageText = pageText,
        actionButtonText = actionButtonText,
        cuisines = cuisines,
        diets = diets,
        intolerances = intolerances,
        onCuisineSelected = recommendationsViewModel::onCuisineSelected,
        onDietSelected = recommendationsViewModel::onDietSelected,
        onIntolerancesSelected = recommendationsViewModel::onIntoleranceSelected,
        onButtonClicked = onButtonClick
    )
}

@Composable
fun RecommendationsLoading(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(30.dp), color = DarkPrimary)
    }
}

@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun RecommendationsScreenContent(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    pagerState: PagerState,
    pageText: String,
    actionButtonText: String,
    cuisines: List<FilterItem>,
    diets: List<FilterItem>,
    intolerances: List<FilterItem>,
    onCuisineSelected: (FilterItem) -> Unit,
    onDietSelected: (FilterItem) -> Unit,
    onIntolerancesSelected: (FilterItem) -> Unit,
    onButtonClicked: () -> Unit
) {
    val recommendationsContentAlpha = animateFloatAsState(
        targetValue = if (enabled) 1f else 0f
    )

    Column(
        modifier = modifier.alpha(recommendationsContentAlpha.value),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedContent(
                targetState = pageText,
                transitionSpec = { textFadeAnimation() }
            ) { text ->
                Text(
                    text = text,
                    modifier = Modifier.padding(bottom = 10.dp),
                    color = Color.Black,
                    fontFamily = SegoeUi,
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = stringResource(R.string.update_later),
                color = Color.LightGray,
                fontFamily = SegoeUi,
                fontSize = 12.sp
            )
            PagesProgressIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .padding(vertical = 40.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            )
        }
        HorizontalPager(
            count = 3,
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 2f),
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            when (page) {
                0 -> RecommendationsPage(
                    modifier = Modifier.fillMaxSize(),
                    recommendations = cuisines,
                    onItemClick = onCuisineSelected
                )
                1 -> RecommendationsPage(
                    modifier = Modifier.fillMaxSize(),
                    recommendations = diets,
                    onItemClick = onDietSelected
                )
                2 -> RecommendationsPage(
                    modifier = Modifier.fillMaxSize(),
                    recommendations = intolerances,
                    onItemClick = onIntolerancesSelected
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.5f),
            contentAlignment = Alignment.Center
        ) {
            ActionButton(
                modifier = Modifier.padding(top = 10.dp),
                text = {
                    AnimatedContent(targetState = actionButtonText, transitionSpec = {
                        textSlideAnimation().using(
                            SizeTransform(clip = false)
                        )
                    }) { text ->
                        Text(
                            text = text,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            color = DarkPrimary,
                            fontFamily = SegoeUi,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                },
                onClick = onButtonClicked
            )
        }
    }
}