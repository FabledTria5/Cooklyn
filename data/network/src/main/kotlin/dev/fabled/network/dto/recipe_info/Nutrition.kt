package dev.fabled.network.dto.recipe_info


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Nutrition(
    @SerialName("caloricBreakdown")
    val caloricBreakdown: CaloricBreakdown,
    @SerialName("flavonoids")
    val flavonoids: List<Flavonoid>,
    @SerialName("ingredients")
    val ingredients: List<IngredientX>,
    @SerialName("nutrients")
    val nutrients: List<NutrientX>,
    @SerialName("properties")
    val properties: List<Property>,
    @SerialName("weightPerServing")
    val weightPerServing: WeightPerServing
)