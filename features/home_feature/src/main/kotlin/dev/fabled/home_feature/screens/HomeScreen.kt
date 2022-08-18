package dev.fabled.home_feature.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fabled.common.components.PrimaryScreenAppBar
import dev.fabled.common.ui.Active
import dev.fabled.common.ui.SegoeUi
import dev.fabled.home_feature.HomeViewModel
import dev.fabled.home_feature.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    Scaffold(topBar = {
        PrimaryScreenAppBar(
            primaryText = "Hi, ${homeViewModel.userName} !",
            secondaryText = stringResource(R.string.home_screen_secondary),
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            )
            DailyRecipeCard(
                modifier = Modifier
                    .padding(start = 15.dp, top = 10.dp, end = 15.dp)
                    .fillMaxWidth()
                    .aspectRatio(ratio = .8f)
            ) {

            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.icon_search),
                tint = Color.Gray
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.searchfield_placeholder),
                color = Color.Gray,
                fontFamily = SegoeUi,
                fontSize = 16.sp
            )
        },
        readOnly = true,
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.LightGray.copy(alpha = .2f),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            textColor = Color.Black,
        )
    )
}

@ExperimentalMaterial3Api
@Composable
fun DailyRecipeCard(modifier: Modifier = Modifier, onCLick: () -> Unit) {
    ElevatedCard(
        onClick = onCLick,
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 15.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.pizza_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Restaurant,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 5.dp),
                    tint = Color.White
                )
                Text(
                    text = "Italian",
                    modifier = Modifier.padding(end = 10.dp),
                    color = Color.White,
                    fontFamily = SegoeUi,
                    fontSize = 12.sp
                )
                Icon(
                    imageVector = Icons.Filled.Schedule,
                    modifier = Modifier.padding(end = 5.dp),
                    contentDescription = null,
                    tint = Color.White
                )
                Text(text = "30 mins", color = Color.White, fontFamily = SegoeUi, fontSize = 12.sp)
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 30.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Active),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.dish_of_the_day),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        color = Color.Black,
                        fontFamily = SegoeUi,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = "Pizzeria Gino Sorbillo",
                    color = Color.White,
                    fontFamily = SegoeUi,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            }
        }
    }
}