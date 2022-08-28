package dev.fabled.network.dto.recipes_list

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RecipesListResponse(
    @SerialName("number")
    val number: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("results")
    val results: List<Result>,
    @SerialName("totalResults")
    val totalResults: Int
)