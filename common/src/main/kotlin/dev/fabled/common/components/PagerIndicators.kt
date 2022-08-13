package dev.fabled.common.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
        items(pagerState.pageCount) { item ->
            if (item == pagerState.currentPage) {
                Canvas(
                    modifier = Modifier
                        .size(width = 35.dp, height = 10.dp)
                        .animateItemPlacement(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessMedium
                            )
                        )
                ) {
                    drawRoundRect(color = Active, cornerRadius = CornerRadius(10f, 10f))
                }
            } else {
                Canvas(modifier = Modifier.size(10.dp).animateItemPlacement(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )) {
                    drawCircle(color = Color.LightGray)
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
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