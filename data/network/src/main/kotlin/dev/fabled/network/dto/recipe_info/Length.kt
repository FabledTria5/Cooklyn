package dev.fabled.network.dto.recipe_info


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Length(
    @SerialName("number")
    val number: Int,
    @SerialName("unit")
    val unit: String
)