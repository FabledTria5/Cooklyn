package dev.fabled.home_feature.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fabled.common.components.PrimaryScreenAppBar
import dev.fabled.common.ui.DarkPrimary
import dev.fabled.common.ui.SegoeUi
import dev.fabled.domain.model.RecipeItem
import dev.fabled.domain.model.Resource
import dev.fabled.home_feature.HomeViewModel
import dev.fabled.home_feature.R
import dev.fabled.home_feature.components.DailyRecipeCard
import dev.fabled.home_feature.utils.KeyboardState
import dev.fabled.home_feature.utils.rememberKeyboardState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val dailyRecipe = homeViewModel.dailyRecipe

    var isSearchEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = !isSearchEnabled,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier.align(Alignment.Start),
        ) {
            PrimaryScreenAppBar(
                primaryText = "Hi, ${homeViewModel.userName} !",
                secondaryText = stringResource(R.string.home_screen_secondary),
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        SearchBar(
            modifier = Modifier
                .padding(vertical = 25.dp)
                .fillMaxWidth(),
            isEnabled = isSearchEnabled,
            onSearch = homeViewModel::onSearchClicked,
            onSearchStateChange = { isSearchEnabled = it }
        )
        AnimatedVisibility(
            visible = !isSearchEnabled,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            DailyRecipe(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
                    .aspectRatio(ratio = 1f),
                dailyRecipe = dailyRecipe,
                onRecipeClicked = homeViewModel::onRecipeClicked
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun DailyRecipe(
    modifier: Modifier = Modifier,
    dailyRecipe: Resource<RecipeItem>,
    onRecipeClicked: (Int) -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when (dailyRecipe) {
            is Resource.Success -> DailyRecipeCard(
                recipeItem = dailyRecipe.data,
                modifier = Modifier.fillMaxSize(),
                onClick = onRecipeClicked
            )
            Resource.Loading -> CircularProgressIndicator(
                color = DarkPrimary,
                modifier = Modifier.size(30.dp)
            )
            is Resource.Error -> CircularProgressIndicator(
                color = DarkPrimary,
                modifier = Modifier.size(30.dp)
            )
            else -> Unit
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchStateChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    isEnabled: Boolean
) {
    var searchQuery by remember { mutableStateOf("") }
    val keyboardState by rememberKeyboardState()

    val focusRequester = FocusRequester()
    val localFocusManager = LocalFocusManager.current

    LaunchedEffect(key1 = keyboardState) {
        snapshotFlow { keyboardState }.collect {
            if (it == KeyboardState.Closed) {
                focusRequester.freeFocus()
                localFocusManager.clearFocus()

                onSearchStateChange(false)
            } else onSearchStateChange(true)
        }
    }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        modifier = modifier.focusRequester(focusRequester),
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
        trailingIcon = {
            AnimatedVisibility(
                visible = isEnabled,
                enter = fadeIn(animationSpec = tween(delayMillis = 500)),
                exit = fadeOut()
            ) {
                Text(
                    text = stringResource(R.string.add),
                    modifier = Modifier.padding(end = 5.dp),
                    color = DarkPrimary,
                    fontFamily = SegoeUi,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        },
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.LightGray.copy(alpha = .2f),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            textColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch(searchQuery) }
        )
    )
}