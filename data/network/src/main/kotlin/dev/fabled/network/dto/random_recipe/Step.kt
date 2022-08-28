package dev.fabled.network.dto.random_recipe

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Step(
    @SerialName("equipment")
    val equipment: List<Equipment>,
    @SerialName("ingredients")
    val ingredients: List<Ingredient>,
    @SerialName("length")
    val length: Length?,
    @SerialName("number")
    val number: Int,
    @SerialName("step")
    val step: String
)