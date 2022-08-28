package dev.fabled.network.dto.recipe_info


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class WeightPerServing(
    @SerialName("amount")
    val amount: Int,
    @SerialName("unit")
    val unit: String
)