package dev.fabled.network.dto.recipe_info


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RecipesInfoResponse(
    @SerialName("aggregateLikes")
    val aggregateLikes: Int,
    @SerialName("analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstruction>,
    @SerialName("cheap")
    val cheap: Boolean,
    @SerialName("cookingMinutes")
    val cookingMinutes: Int,
    @SerialName("creditsText")
    val creditsText: String,
    @SerialName("cuisines")
    val cuisines: List<String>,
    @SerialName("dairyFree")
    val dairyFree: Boolean,
    @SerialName("diets")
    val diets: List<String>,
    @SerialName("dishTypes")
    val dishTypes: List<String>,
    @SerialName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient>,
    @SerialName("gaps")
    val gaps: String,
    @SerialName("glutenFree")
    val glutenFree: Boolean,
    @SerialName("healthScore")
    val healthScore: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("imageType")
    val imageType: String,
    @SerialName("instructions")
    val instructions: String,
    @SerialName("license")
    val license: String,
    @SerialName("lowFodmap")
    val lowFodmap: Boolean,
    @SerialName("nutrition")
    val nutrition: Nutrition,
    @SerialName("preparationMinutes")
    val preparationMinutes: Int,
    @SerialName("pricePerServing")
    val pricePerServing: Double,
    @SerialName("readyInMinutes")
    val readyInMinutes: Int,
    @SerialName("servings")
    val servings: Int,
    @SerialName("sourceName")
    val sourceName: String,
    @SerialName("sourceUrl")
    val sourceUrl: String,
    @SerialName("spoonacularSourceUrl")
    val spoonacularSourceUrl: String,
    @SerialName("summary")
    val summary: String,
    @SerialName("sustainable")
    val sustainable: Boolean,
    @SerialName("title")
    val title: String,
    @SerialName("vegan")
    val vegan: Boolean,
    @SerialName("vegetarian")
    val vegetarian: Boolean,
    @SerialName("veryHealthy")
    val veryHealthy: Boolean,
    @SerialName("veryPopular")
    val veryPopular: Boolean,
    @SerialName("weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int,
    @SerialName("winePairing")
    val winePairing: WinePairing
)