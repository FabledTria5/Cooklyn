package dev.fabled.common.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dev.fabled.common.ui.Active
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun PagerIndicators(pagerState: PagerState, modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier) {
        (0 until pagerState.pageCount).forEach { page ->
            if (page == pagerState.currentPage) {
                item {
                    Canvas(
                        modifier = Modifier
                            .size(width = 30.dp, height = 5.dp)
                            .animateItemPlacement(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessMedium
                                )
                            )
                    ) {
                        drawRoundRect(color = Active, cornerRadius = CornerRadius(15f, 15f))
                    }
                }
            } else
                item {
                    Canvas(
                        modifier = Modifier
                            .size(5.dp)
                            .animateItemPlacement(
                                animationSpec = tween()
                            )
                    ) {
                        drawCircle(color = Color.LightGray)
                    }
                }
            item {
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun PagerIndicatorsPreview() {
    val pagerState = rememberPagerState()
    val coroutine = rememberCoroutineScope()

    Column {
        HorizontalPager(
            count = 4,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clickable {
                    coroutine.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
        ) {

        }

        PagerIndicators(
            pagerState = pagerState, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
    }
}