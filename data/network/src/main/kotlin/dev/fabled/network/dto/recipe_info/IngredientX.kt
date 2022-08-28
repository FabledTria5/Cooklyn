package dev.fabled.network.dto.recipe_info


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class IngredientX(
    @SerialName("amount")
    val amount: Double,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("nutrients")
    val nutrients: List<NutrientX>,
    @SerialName("unit")
    val unit: String
)