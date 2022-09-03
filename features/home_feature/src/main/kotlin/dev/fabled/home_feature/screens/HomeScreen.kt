package dev.fabled.home_feature.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fabled.common.components.PrimaryScreenAppBar
import dev.fabled.common.components.RemoteImage
import dev.fabled.common.ui.Active
import dev.fabled.common.ui.DarkPrimary
import dev.fabled.common.ui.SegoeUi
import dev.fabled.domain.model.IngredientItem
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
    val selectedIngredients = homeViewModel.selectedIngredients

    var isSearchEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
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
                modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)
            )
        }
        SearchBar(
            modifier = Modifier
                .padding(vertical = 25.dp, horizontal = 10.dp)
                .fillMaxWidth(),
            isEnabled = isSearchEnabled,
            onSearch = homeViewModel::onSearchClicked,
            onSearchStateChange = { isSearchEnabled = it },
            onAddIngredientsClicked = homeViewModel::onAddIngredientsClicked
        )
        AnimatedVisibility(
            visible = isSearchEnabled,
            enter = fadeIn(animationSpec = tween(durationMillis = 100, delayMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 100))
        ) {
            SelectedIngredients(
                modifier = Modifier.fillMaxWidth(),
                ingredients = selectedIngredients
            )
        }
        AnimatedVisibility(
            visible = !isSearchEnabled,
            enter = fadeIn(animationSpec = tween(delayMillis = 300)),
            exit = fadeOut()
        ) {
            DailyRecipe(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
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
    onAddIngredientsClicked: () -> Unit,
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
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .clickable { onAddIngredientsClicked() },
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
            cursorColor = Active
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

@Composable
fun SelectedIngredients(modifier: Modifier = Modifier, ingredients: List<IngredientItem>) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        itemsIndexed(
            items = ingredients,
            key = { _, item -> item.id }
        ) { index, ingredient ->
            SelectedIngredientItem(ingredientItem = ingredient)
            if (index != ingredients.lastIndex)
                Spacer(modifier = Modifier.padding(end = 10.dp))
        }
    }
}

@Composable
fun SelectedIngredientItem(ingredientItem: IngredientItem) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = DarkPrimary, shape = RoundedCornerShape(10.dp))
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RemoteImage(
            imagePath = ingredientItem.image,
            contentDescription = ingredientItem.name,
            modifier = Modifier
                .padding(end = 10.dp)
                .size(45.dp)
        )
        Text(
            text = ingredientItem.name,
            modifier = Modifier.padding(end = 10.dp),
            color = DarkPrimary,
            fontFamily = SegoeUi,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientPrev() {
    Box(modifier = Modifier.padding(20.dp)) {
        SelectedIngredientItem(
            ingredientItem = IngredientItem(
                id = 1,
                name = "Baby Carrots",
                image = "https://spoonacular.com/cdn/ingredients_100x100/baby-corn.jpg"
            )
        )
    }
}