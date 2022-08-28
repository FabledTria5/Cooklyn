package dev.fabled.network.dto.recipe_info


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Property(
    @SerialName("amount")
    val amount: Double,
    @SerialName("name")
    val name: String,
    @SerialName("unit")
    val unit: String
)